package ru.amalkoott.findjob.ui.app.screens.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TempMessage(text: String){
    Box(
        Modifier.padding(horizontal = 16.dp).fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            modifier = Modifier,
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            textAlign = TextAlign.Center
        )
    }
}