package ru.amalkoott.findjob.ui.app.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import ru.amalkoott.findjob.R
import kotlinx.coroutines.launch
import ru.amalkoott.findjob.domain.entities.Offer
import ru.amalkoott.findjob.domain.entities.Vacancy
import ru.amalkoott.findjob.domain.tools.Formatter.getVacancyText
import ru.amalkoott.findjob.ui.app.strategy.OfferDisplayStrategy
import ru.amalkoott.findjob.ui.app.strategy.TopBarDisplayStrategy
import ru.amalkoott.findjob.ui.app.strategy.VacancyCardDisplayStrategy
import ru.amalkoott.findjob.ui.app.components.LoadingFiller
import ru.amalkoott.findjob.ui.app.view.HomeViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun SearchContent(name: String, onClick: () -> Unit, viewModel: HomeViewModel) {
    val isOfferLoading by viewModel.isOfferLoading.collectAsState()
    val isVacanciesLoading by viewModel.isVacanciesLoading.collectAsState()
    val vacancies by viewModel.vacancies.collectAsState(initial = emptyList())
    val offers by viewModel.offers.collectAsState(initial = emptyList())
    val expanded by viewModel.isVacancyExpanded
    val onFavoriteClick:(Vacancy) -> Unit = { vacancy -> viewModel.toggleFavorite(vacancy)}
    val onVacancyClick:(Vacancy) -> Unit = { vacancy -> viewModel.openVacancy(vacancy); onClick() }
    val onActionClick = { if(expanded) { viewModel.toggleExpand() } else { /* TODO ACTION */ } }
    val onFilterClick = { /* TODO ACTION */ }
    val lazyListState = rememberLazyListState()
    val topBarStrategy = viewModel.getDisplayStrategy(expanded)
    val scope = rememberCoroutineScope()
        if(expanded) {
            Box(modifier = Modifier.fillMaxWidth()
                .height(64.dp)
                .background(MaterialTheme.colorScheme.background)
                .shadow(elevation = 8.dp, ambientColor = Color.Black, shape = RectangleShape).zIndex(4f)){
                TopBar(topBarStrategy,onActionClick,onFilterClick)

            }
        }

        LazyColumn(state = lazyListState){
            if(!expanded){
                item{
                    TopBar(topBarStrategy, onActionClick, onFilterClick)
                }
                item {
                    Row(
                        modifier = Modifier
                            .padding(bottom = 32.dp, top = 16.dp)
                            .fillMaxWidth()
                            .layout{measurable, constraints ->
                                val placeable = measurable.measure(constraints.copy(
                                    maxWidth = constraints.maxWidth + 16.dp.roundToPx(),
                                ))
                                layout(placeable.width, placeable.height){
                                    placeable.place(8.dp.roundToPx(),0)
                                }
                            }
                    ) {

                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            if (isOfferLoading) {
                                item{
                                    Spacer(Modifier.width(8.dp))
                                }
                                items(3) {
                                    LoadingFiller(Modifier.size(height = 120.dp, width = 132.dp))
                                }
                                item{
                                    Spacer(Modifier.width(16.dp))
                                }
                            }else{
                                item{
                                    Spacer(Modifier.width(8.dp))
                                }
                                items(items = offers){ offer ->
                                    val strategy = viewModel.getDisplayStrategy(offer)
                                    OfferCard(offer, strategy)
                                }
                                item{
                                    Spacer(Modifier.width(16.dp))
                                }
                            }
                        }
                    }
                }
                item{
                    Text(
                        text = "Вакансии для вас",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                    )
                }
            }else{
                item{
                    Row(modifier = Modifier
                        .padding(top = 70.dp, bottom = 25.dp, start = 16.dp, end = 16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = getVacancyText(vacancies.size),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),) {
                            Text(
                                text = "По соответствию",
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Icon(
                                painter = painterResource(R.drawable.ic_launcher_sorter_foreground),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(16.dp))
                        }
                    }
                }
            }

            if(isVacanciesLoading){
                items(3){
                    LoadingFiller(Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp).height(286.dp).fillMaxWidth())
                }
            }else{
                items(items = if (!expanded) vacancies.take(3) else vacancies){ vacancy ->
                    val strategy = viewModel.getDisplayStrategy(vacancy)
                    VacancyCard(vacancy, strategy,onVacancyClick, onFavoriteClick)
                }
                if(!expanded) {
                    item {
                        Button(
                            onClick = {
                                scope.launch { lazyListState.scrollToItem(0) }
                                viewModel.toggleExpand() },
                            colors = ButtonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.onSurface,
                                disabledContainerColor = MaterialTheme.colorScheme.secondary,
                                disabledContentColor = MaterialTheme.colorScheme.onSecondary,
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp, bottom = 72.dp, start = 16.dp, end = 16.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = 8.dp),
                                text = "Еще " + viewModel.getShowMoreText(),
                                style = MaterialTheme.typography.displayLarge,
                            )
                        }

                    }
                }else item{
                    Spacer(modifier = Modifier.padding(34.dp))
                }
            }
        }
}



@Composable
fun OfferCard(
    offer: Offer,
    strategy: OfferDisplayStrategy
) {
    strategy.display(offer)
}

@Composable
fun VacancyCard(
    vacancy: Vacancy,
    strategy: VacancyCardDisplayStrategy,
    onClick: (Vacancy) -> Unit,
    onFavoriteClick: (Vacancy) -> Unit) {
    strategy.display(vacancy,onClick,onFavoriteClick)
}

@Composable
fun TopBar(
    strategy: TopBarDisplayStrategy,
    onActionClick: () -> Unit,
    onFilterClick: () -> Unit
) {
    strategy.display(onActionClick,onFilterClick)
}