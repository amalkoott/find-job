package ru.amalkoott.findjob.data.remote

import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import ru.amalkoott.findjob.data.mapper.DataMapper
import ru.amalkoott.findjob.domain.AppRemoteRepository
import ru.amalkoott.findjob.domain.entities.Offer
import ru.amalkoott.findjob.domain.entities.Vacancy

class ServerRequestRepository (private val apiService: ApiService): AppRemoteRepository {
    override suspend fun get(): Pair<Flow<List<Offer>>, List<Vacancy>> = withContext(Dispatchers.IO){
        val fetchedData: JsonObject
        try {
            val response = apiService.get()
            if(!response.isSuccessful){
                return@withContext Pair(MutableStateFlow(emptyList()), emptyList())
            }
            fetchedData = response.body()!!.asJsonObject

        }
        catch (e: Exception){
            return@withContext Pair(MutableStateFlow(emptyList()),emptyList())
        }
        val offers = fetchedData.asJsonObject.get("offers").asJsonArray
        val vacancies = fetchedData.asJsonObject.get("vacancies").asJsonArray

        return@withContext Pair(DataMapper.offerMap(offers),DataMapper.vacancyMap(vacancies))
    }
}
