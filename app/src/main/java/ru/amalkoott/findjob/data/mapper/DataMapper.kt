package ru.amalkoott.findjob.data.mapper

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.amalkoott.findjob.domain.entities.Offer
import ru.amalkoott.findjob.domain.entities.Vacancy

object DataMapper {
    fun offerMap(offers: JsonArray): Flow<List<Offer>> {
        val res = offers.map {
            toOffer(it)
        } ?: emptyList()
        return MutableStateFlow(res)
    }
    fun vacancyMap(vacancies: JsonArray): List<Vacancy>{
        val res = vacancies.map {
            toVacancy(it)
        } ?: emptyList()
        return res
    }

    private fun toOffer(request: JsonElement): Offer {
        val jsonAdvert = request.asJsonObject

        return Offer(
            id = jsonAdvert.get("id").toStringOrNull(),
            title = jsonAdvert.get("title").toStringOrNull(),
            link = jsonAdvert.get("link").toStringOrNull(),
            buttonText = jsonAdvert.getOrNull("button")?.get("text").toStringOrNull()//.getOrNull("text").toStringOrNull()
        )
    }
    private fun toVacancy(request: JsonElement): Vacancy {
        val jsonVacancy = request.asJsonObject

        val vacancy = Vacancy(
            id = jsonVacancy.get("id").toStringOrNull(),
            lookingNumber = jsonVacancy.get("lookingNumber").toIntOrNull(),
            title = jsonVacancy.get("title").toStringOrNull(),
            town = jsonVacancy.get("address").asJsonObject.get("town").toStringOrNull(),
            street = jsonVacancy.get("address").asJsonObject.get("street").toStringOrNull(),
            house = jsonVacancy.get("address").asJsonObject.get("house").toStringOrNull(),
            company = jsonVacancy.get("company").toStringOrNull(),
            experiencePreview = jsonVacancy.get("experience").asJsonObject.get("previewText").toStringOrNull(),
            experienceText = jsonVacancy.get("experience").asJsonObject.get("text").toStringOrNull(),
            publishedDate = jsonVacancy.get("publishedDate").toStringOrNull(),
            isFavorite = jsonVacancy.get("isFavorite").asBoolean,
            salary = jsonVacancy.get("salary").asJsonObject.toMapOrNul(),
            schedules = jsonVacancy.get("schedules").asJsonArray.toListString(),
            appliedNumber = jsonVacancy.get("appliedNumber").toIntOrNull(),
            description = jsonVacancy.get("description").toStringOrNull(),
            responsibilities = jsonVacancy.get("responsibilities").toStringOrNull(),
            questions = jsonVacancy.get("questions").asJsonArray.toListString()
        )

        return vacancy
    }

    private fun JsonObject.getOrNull(name: String): JsonObject?{
        try {
            val result = if (this.isJsonObject) this.get(name).asJsonObject else {
                val gson = Gson()
                gson.toJsonTree(this).asJsonObject
            }
            return result
        }
        catch (_e: Exception){ return null }
    }
    private fun JsonElement?.toIntOrNull(): Int?{
        try {
            return this!!.asInt
        }catch (_e: Exception){
            return null
        }
    }
    private fun JsonElement?.toStringOrNull(): String{
        try {
            return this!!.asString
        }catch (_e: Exception){
            return "null"
        }
    }
    private fun JsonObject.toMapOrNul():Map<String,String>?{
        try {
            val gson = Gson()
            val res = gson.fromJson(this.toString(),Map::class.java) as Map<String, String>
            return res
        }catch (_e: Exception){
            return null
        }
    }
    private fun JsonArray.toListString(): List<String>{
        return this.map {
            it.asString
        } ?: emptyList()
    }
}









