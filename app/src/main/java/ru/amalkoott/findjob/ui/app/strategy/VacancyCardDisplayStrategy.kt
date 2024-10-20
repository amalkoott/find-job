package ru.amalkoott.findjob.ui.app.strategy

import androidx.compose.runtime.Composable
import ru.amalkoott.findjob.domain.entities.Vacancy

interface VacancyCardDisplayStrategy {
    @Composable
    fun display(
        vacancy: Vacancy,
        onClick: (Vacancy) -> Unit,
        onFavoriteClick: (Vacancy) -> Unit)
}