package ru.amalkoott.findjob.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.amalkoott.findjob.domain.entities.Vacancy

@Dao
interface AppDao {
    // INSERT
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVacancy(vacancy: Vacancy): Long

    // SELECT
    @Query("SELECT * FROM Vacancy")
    fun allVacancy():Flow<List<Vacancy>>

    @Query("SELECT * FROM Vacancy WHERE isFavorite = 1")
    fun allFavorites():Flow<List<Vacancy>>

    @Query("SELECT count(*) FROM Vacancy WHERE isFavorite = 1")
    fun countFavorites():Flow<Int>

    // UPDATE
    @Update
    suspend fun updateVacancy(vacancy: Vacancy)

    // DELETE
    @Query("DELETE FROM Vacancy")
    fun deleteVacancy()
}