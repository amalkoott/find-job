package ru.amalkoott.findjob.domain.tools

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object Formatter {
    fun getVacancyText(count: Int): String{
        return if(count < 1) "Нет вакансий" else "$count ${getVacancyWord(count)}"
    }
    private fun getVacancyWord(count: Int): String {
        return when {
            count % 10 == 1 && count % 100 != 11 -> "вакансия"
            count % 10 in 2..4 && count % 100 !in 12..14 -> "вакансии"
            else -> "вакансий"
        }
    }

    fun getPeopleText(count: Int): String{
        val count = count
        return "$count ${getPeopleWord(count)}"
    }
    private fun getPeopleWord(count: Int): String {
        return when {
            count % 10 == 1 && count % 100 != 11 -> "человек"
            count % 10 in 2..4 && count % 100 !in 12..14 -> "человека"
            else -> "человек"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(dateString: String): String {
        val date = LocalDate.parse(dateString)
        return date.format(DateTimeFormatter.ofPattern("d MMMM", Locale.forLanguageTag("ru")))
    }
}