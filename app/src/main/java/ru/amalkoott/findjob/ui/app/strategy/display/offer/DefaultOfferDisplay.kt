package ru.amalkoott.findjob.ui.app.strategy.display.offer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.amalkoott.findjob.domain.entities.Offer
import ru.amalkoott.findjob.ui.app.strategy.OfferDisplayStrategy
import ru.amalkoott.findjob.ui.app.components.OfferIcon

class DefaultOfferDisplay : OfferDisplayStrategy {
    @Composable
    override fun display(offer: Offer) {
        Card(
            modifier = Modifier
                .size(width = 132.dp, height = 120.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 10.dp, start = 8.dp, end = 16.dp, bottom = 11.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OfferIcon(offer.id)
                Column {
                    offer.title?.let {
                        Text(
                            text = it.trimStart(),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 3
                        )
                    }
                }
            }
        }
    }
}
