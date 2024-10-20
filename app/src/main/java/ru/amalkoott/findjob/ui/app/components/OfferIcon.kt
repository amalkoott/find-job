package ru.amalkoott.findjob.ui.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.amalkoott.findjob.R


@Composable
fun OfferIcon(id: String?){
    val modifier = Modifier.padding(6.dp).fillMaxSize()
    var resource: Int? = null
    var tint: Color? = null
    var color: Color? = null
    when(id){
        "near_vacancies" -> {
            resource = (R.drawable.ic_launcher_near_vacancies_foreground)
            color = MaterialTheme.colorScheme.secondaryContainer
            tint = MaterialTheme.colorScheme.secondary
        }
        "level_up_resume" -> {
            resource = (R.drawable.ic_launcher_level_up_foreground)
            color = MaterialTheme.colorScheme.tertiaryContainer
            tint = MaterialTheme.colorScheme.tertiary
        }
        "temporary_job" -> {
            resource = (R.mipmap.ic_launcher_temporary_job_foreground)
            color = MaterialTheme.colorScheme.tertiaryContainer
            tint = MaterialTheme.colorScheme.tertiary
        }
        else -> null
    }
    if(resource != null && tint != null && color != null){
        Box(
            modifier = Modifier
                .size(width = 32.dp, height = 32.dp)
                .clip(CircleShape)
                .background(color = color)
        ) {
            Icon(
                painterResource(resource),
                contentDescription = id,
                modifier = modifier,
                tint = tint,
            )
        }
    }
}