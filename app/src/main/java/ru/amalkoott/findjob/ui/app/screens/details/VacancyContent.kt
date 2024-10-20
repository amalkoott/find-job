package ru.amalkoott.findjob.ui.app.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import ru.amalkoott.findjob.domain.entities.Vacancy
import ru.amalkoott.findjob.ui.app.screens.content.TempMessage

@Composable
fun VacancyContent(name: String, onClick: () -> Unit, vacancy: State<Vacancy?>){
    TempMessage(vacancy.value?.title.toString())
}
