package ru.amalkoott.findjob.ui.app.strategy

import androidx.compose.runtime.Composable
import ru.amalkoott.findjob.domain.entities.Offer

interface OfferDisplayStrategy {
    @Composable
    fun display(offer: Offer)
}