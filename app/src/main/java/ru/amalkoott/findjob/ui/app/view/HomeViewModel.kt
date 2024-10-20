package ru.amalkoott.findjob.ui.app.view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.amalkoott.findjob.ui.HomeScreen
import ru.amalkoott.findjob.domain.AppUseCase
import ru.amalkoott.findjob.domain.entities.Offer
import ru.amalkoott.findjob.domain.entities.Vacancy
import ru.amalkoott.findjob.domain.tools.Formatter.getVacancyText
import ru.amalkoott.findjob.ui.app.strategy.OfferDisplayStrategy
import ru.amalkoott.findjob.ui.app.strategy.TopBarDisplayStrategy
import ru.amalkoott.findjob.ui.app.strategy.VacancyCardDisplayStrategy
import ru.amalkoott.findjob.ui.app.strategy.display.card.DefaultVacancyCardDisplay
import ru.amalkoott.findjob.ui.app.strategy.display.card.NowLookingVacancyCardDisplay
import ru.amalkoott.findjob.ui.app.strategy.display.offer.DefaultOfferDisplay
import ru.amalkoott.findjob.ui.app.strategy.display.offer.OfferWithButtonDisplay
import ru.amalkoott.findjob.ui.app.strategy.display.topbar.DefaultTopBarDisplay
import ru.amalkoott.findjob.ui.app.strategy.display.topbar.TopBarWithExpandedDisplay
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val appUseCase: AppUseCase
): ViewModel() {
    private val _vacancies = MutableStateFlow<List<Vacancy>>(emptyList())
    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    private val _favorites = MutableStateFlow<List<Vacancy>>(emptyList())
    private val _favoritesCounter = MutableStateFlow<Int>(0)
    private var _isVacancyExpanded = mutableStateOf(false)
    private val _selectedVacancy = MutableStateFlow<Vacancy?>(null)
    private val _isOfferLoading = MutableStateFlow(true)
    private val _isVacanciesLoading = MutableStateFlow(true)

    val vacancies: Flow<List<Vacancy>> get() = _vacancies
    val offers: Flow<List<Offer>> get() = _offers
    val favorites: Flow<List<Vacancy>> get() = _favorites
    val isVacancyExpanded: State<Boolean> get() = _isVacancyExpanded
    val selectedVacancy: StateFlow<Vacancy?> = _selectedVacancy
    val isOfferLoading: StateFlow<Boolean> = _isOfferLoading
    val isVacanciesLoading: StateFlow<Boolean> = _isVacanciesLoading

    init {
        viewModelScope.launch {
            _isOfferLoading.value = true
            val offersDeferred = async { fetchOffers() }
            val otherDataDeferred = async { loadOtherData() }

            offersDeferred.await()
            otherDataDeferred.await()
        }
    }
    private suspend fun fetchOffers() {
        appUseCase.fetchData().collect { offer ->
            _offers.value = offer
            _isOfferLoading.value = false
        }
    }

    private suspend fun loadOtherData() = withContext(Dispatchers.IO) {
        val vacanciesDeferred = async { loadVacancies() }
        val favoritesDeferred = async { loadFavorites() }

        vacanciesDeferred.await()
        favoritesDeferred.await()
    }

    private suspend fun loadVacancies() {
        appUseCase.getData().collect { vacancy ->
            withContext(Dispatchers.Main) {
                _vacancies.value = vacancy
                _isVacanciesLoading.value = false
            }
        }
    }
    private suspend fun loadFavorites() {
        appUseCase.getFavorites().collect { favoritesList ->
            withContext(Dispatchers.Main) {
                _favorites.value = favoritesList
                _favoritesCounter.value = favoritesList.size
            }
        }
    }
    fun getDisplayStrategy(offer : Offer): OfferDisplayStrategy {
        return when {
            offer.buttonText != "null" -> OfferWithButtonDisplay()
            offer.title != null && offer.link != null -> DefaultOfferDisplay()
            else -> DefaultOfferDisplay()
        }
    }
    fun getDisplayStrategy(vacancy: Vacancy): VacancyCardDisplayStrategy {
        return if(vacancy.lookingNumber != null) NowLookingVacancyCardDisplay() else DefaultVacancyCardDisplay()
    }
    fun getDisplayStrategy(expanded: Boolean): TopBarDisplayStrategy {
        return if (expanded) TopBarWithExpandedDisplay() else DefaultTopBarDisplay()
    }

    fun toggleExpand(){
        _isVacancyExpanded.value = !_isVacancyExpanded.value
    }
    fun toggleFavorite(vacancy: Vacancy){
        viewModelScope.launch {
            appUseCase.updateVacancy(vacancy.copy(isFavorite = !vacancy.isFavorite))
        }
    }

    fun onRemoveFavorite(vacancy: Vacancy){
        viewModelScope.launch {
            appUseCase.updateVacancy(vacancy.copy(isFavorite = !vacancy.isFavorite))
        }
    }

    fun openVacancy(vacancy: Vacancy){
        _selectedVacancy.value = vacancy
    }

    fun getShowMoreText(): String{
        val count = _vacancies.value.size
        return if (count < 1) "Еще вакансии" else getVacancyText(count - 3)
    }

    fun getCounter(item: HomeScreen): StateFlow<Int>?{
        when(item){
            HomeScreen.Favorites -> return _favoritesCounter
            HomeScreen.Home -> return null
            else -> return null
        }
    }
}