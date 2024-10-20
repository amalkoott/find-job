package ru.amalkoott.findjob.ui.app.view

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.amalkoott.findjob.domain.AppUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    //private val appUseCase: AppUseCase
): ViewModel() {
    private val _enteredValue = MutableStateFlow<String?>(null)
    val enteredValue: StateFlow<String?> = _enteredValue

    fun setEnteredValue(value: String){
        _enteredValue.value = value
    }
}