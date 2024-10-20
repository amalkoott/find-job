package ru.amalkoott.findjob.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vacancy(
    @PrimaryKey
    val id: String,
    val lookingNumber: Int?,
    val title: String?,
    val town: String?, // address
    val street: String?, // address
    val house: String?, // address
    val company: String?,
    val experiencePreview: String?, // exp
    val experienceText: String?, // exp
    val publishedDate: String?,
    var isFavorite: Boolean,
    val salary: Map<String,String>?,
    val schedules: List<String>?,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String?,
    val questions: List<String>,
)
