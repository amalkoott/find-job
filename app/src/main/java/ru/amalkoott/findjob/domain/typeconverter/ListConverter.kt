package ru.amalkoott.findjob.domain.typeconverter

import androidx.room.TypeConverter

class ListConverter {
    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return value?.joinToString(",")
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.split(",")
    }
}