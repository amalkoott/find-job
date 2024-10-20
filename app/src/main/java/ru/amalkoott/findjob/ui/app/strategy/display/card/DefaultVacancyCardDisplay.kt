package ru.amalkoott.findjob.ui.app.strategy.display.card

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.amalkoott.findjob.R
import ru.amalkoott.findjob.domain.entities.Vacancy
import ru.amalkoott.findjob.domain.tools.Formatter.formatDate
import ru.amalkoott.findjob.ui.app.strategy.VacancyCardDisplayStrategy

class DefaultVacancyCardDisplay : VacancyCardDisplayStrategy {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun display(
        vacancy: Vacancy,
        onClick: (Vacancy) -> Unit,
        onFavoriteClick: (Vacancy) -> Unit) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
            .clickable { onClick(vacancy) },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),){
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 21.dp),
                ) {
                    Column(modifier = Modifier
                        .weight(1f)) {
                        Column(modifier = Modifier
                            .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)){
                            Text(
                                text = vacancy.title.toString(),
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            if(vacancy.salary != null){
                                val text = vacancy.salary["short"]?.replace(" до ", "-").toString()
                                if(text !== "null"){
                                    Text(
                                        text = text,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }

                            Text(
                                text = vacancy.town.toString(),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ){
                                Text(
                                    text = vacancy.company.toString(),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Icon(
                                    painter = painterResource(R.mipmap.ic_launcher_confirm_foreground),
                                    contentDescription = "confirmed company",
                                    modifier = Modifier.size(16.dp),
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(painterResource(R.mipmap.ic_launcher_expierense_foreground), contentDescription = "",
                                    modifier = Modifier.padding(end = 10.dp).size(16.dp))
                                Text(
                                    text = vacancy.experiencePreview.toString(),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            Text(
                                text = "Опубликовано " + formatDate(vacancy.publishedDate.toString()),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    Column(modifier = Modifier
                        .padding(start = 48.dp)) {
                        Box(modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .clickable { onFavoriteClick(vacancy) }
                        ){
                            Icon(
                                painter = painterResource(if (vacancy.isFavorite) R.mipmap.ic_launcher_favorite_filled_foreground else R.drawable.ic_launcher_favorite_foreground),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                tint = if(vacancy.isFavorite) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSecondary)
                        }
                    }
                }

                Button(
                    onClick = { onClick(vacancy) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        disabledContentColor = MaterialTheme.colorScheme.tertiaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                    )
                ) {
                    Text(
                        text = "Откликнуться",
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
