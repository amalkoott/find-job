package ru.amalkoott.findjob.ui.app.strategy

import androidx.compose.runtime.Composable

interface TopBarDisplayStrategy {
    @Composable
    fun display(onActionClick: () -> Unit, onFilterClick: () -> Unit)
}
