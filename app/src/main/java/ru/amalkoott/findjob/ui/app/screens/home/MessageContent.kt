package ru.amalkoott.findjob.ui.app.screens.home

import androidx.compose.runtime.Composable
import ru.amalkoott.findjob.ui.app.screens.content.TempMessage

@Composable
fun MessageContent(name: String, onClick: () -> Unit) {
    TempMessage(name)
}