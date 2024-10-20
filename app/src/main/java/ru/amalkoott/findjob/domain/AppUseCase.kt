package ru.amalkoott.findjob.domain

import kotlinx.coroutines.flow.Flow
import ru.amalkoott.findjob.data.remote.ServerRequestRepository
import ru.amalkoott.findjob.domain.entities.Offer
import ru.amalkoott.findjob.domain.entities.Vacancy
import javax.inject.Inject

class AppUseCase @Inject constructor (
    private val appApi: ServerRequestRepository,
    private val appRepo: AppRepository
){
    suspend fun fetchData(): Flow<List<Offer>>{
        val data = appApi.get()
        saveData(data.second)
        return data.first
    }
    private suspend fun saveData(data: List<Vacancy>){
        appRepo.fillDatabase(data)
    }
    suspend fun getData(): Flow<List<Vacancy>> {
        return appRepo.loadAllVacancy()
    }
    suspend fun updateVacancy(vacancy: Vacancy){
        appRepo.updateVacancy(vacancy)
    }
    suspend fun getFavorites() : Flow<List<Vacancy>>{
        return appRepo.loadFavorites()
    }
    suspend fun getFavoritesCount() : Flow<Int>{
        return appRepo.countFavorites()
    }
}