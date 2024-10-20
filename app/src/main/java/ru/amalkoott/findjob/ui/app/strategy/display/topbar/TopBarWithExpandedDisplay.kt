package ru.amalkoott.findjob.ui.app.strategy.display.topbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.amalkoott.findjob.R
import ru.amalkoott.findjob.ui.app.strategy.TopBarDisplayStrategy
import ru.amalkoott.findjob.ui.app.components.TopBar

class TopBarWithExpandedDisplay : TopBarDisplayStrategy {
    @Composable
    override fun display(onActionClick: () -> Unit, onFilterClick: () -> Unit) {
        TopBar(
            value = "",
            onValueChange = {},
            label = "String",
            leadingIcon = R.drawable.ic_launcher_left_arrow_foreground,
            modifier = Modifier.padding(top = 16.dp).height(40.dp).fillMaxWidth(),
            textColor = MaterialTheme.colorScheme.onSurface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            toolColor = MaterialTheme.colorScheme.onSurface,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            placeholder = "Должность по подходящим вакансиям",
            placeholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            padding = 4.dp,
            navigateAction = { onActionClick() },
            extraAction = { onFilterClick() }
        )

    }
}