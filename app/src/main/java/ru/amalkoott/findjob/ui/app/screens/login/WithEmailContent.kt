package ru.amalkoott.findjob.ui.app.screens.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.StateFlow


@Composable
fun WithEmailContent(name: StateFlow<String?>, onClick: () -> Unit) {
    val text = name.collectAsState()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(start = 16.dp, top = 162.dp, end = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Отправили код на ${text.value}",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
        )
        Text(
            text = "Напишите его, чтобы подтвердить, что это вы, а не кто-то другой входит в личный кабинет",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
            fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
            .fillMaxWidth()
        ){
            for (i in 0..3) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .clickable { Toast.makeText(context,"Скоро будет сделано!",Toast.LENGTH_SHORT).show() }
                )
            }
        }
        Button(
            onClick = {  onClick() },
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSurface,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                disabledContentColor = MaterialTheme.colorScheme.onSecondary,
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier,
                text = "Подтвердить",
                fontSize = MaterialTheme.typography.displayLarge.fontSize,
                fontWeight = MaterialTheme.typography.displayLarge.fontWeight,
                fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
            )
        }
    }
}