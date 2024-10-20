package ru.amalkoott.findjob.domain

import kotlinx.coroutines.flow.Flow
import ru.amalkoott.findjob.domain.entities.Offer
import ru.amalkoott.findjob.domain.entities.Vacancy

interface AppRemoteRepository {
    suspend fun get(): Pair<Flow<List<Offer>>,List<Vacancy>>?
}