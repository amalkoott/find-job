package ru.amalkoott.findjob.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.amalkoott.findjob.domain.entities.Vacancy
import ru.amalkoott.findjob.domain.typeconverter.ListConverter
import ru.amalkoott.findjob.domain.typeconverter.MapConverter

@Database(
    entities = [Vacancy::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(ListConverter::class, MapConverter::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun notesDao(): AppDao

}