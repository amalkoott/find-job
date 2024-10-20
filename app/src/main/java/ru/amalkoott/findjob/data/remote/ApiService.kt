package ru.amalkoott.findjob.data.remote
import retrofit2.Response
import retrofit2.http.GET
import com.google.gson.JsonObject

interface ApiService{
    @GET("/u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun get(): Response<JsonObject?>//List<Vacancy>//Response<JsonArray?>
}