package ru.amalkoott.findjob.data.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.amalkoott.findjob.domain.AppRepository
import ru.amalkoott.findjob.domain.entities.Vacancy
import javax.inject.Inject

class AppRepository_Impl @Inject constructor(
    private val appDao: AppDao
): AppRepository {

    override suspend fun loadAllVacancy(): Flow<List<Vacancy>> = withContext(Dispatchers.IO){
        return@withContext appDao.allVacancy()
    }
    override suspend fun loadFavorites(): Flow<List<Vacancy>> = withContext(Dispatchers.IO){
        return@withContext appDao.allFavorites()
    }

    override suspend fun countFavorites(): Flow<Int> = withContext(Dispatchers.IO){
        return@withContext appDao.countFavorites()
    }

    override suspend fun addVacancy(vacancy: Vacancy): Long = withContext(Dispatchers.IO) {
        appDao.insertVacancy(vacancy)
    }

    override suspend fun updateVacancy(vacancy: Vacancy) = withContext(Dispatchers.IO) {
        appDao.updateVacancy(vacancy)
    }

    override suspend fun clearDatabase() = withContext(Dispatchers.IO){
        appDao.deleteVacancy()
    }

    override suspend fun fillDatabase(data : List<Vacancy>) {
        data.forEach{
            appDao.insertVacancy(it)
        }
    }

}