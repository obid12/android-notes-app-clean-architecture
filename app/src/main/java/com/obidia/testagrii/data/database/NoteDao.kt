package com.obidia.testagrii.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.obidia.testagrii.domain.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(data: NoteEntity)

    @Update
    suspend fun updateUser(data: NoteEntity)

    @Delete
    suspend fun deleteUser(data: NoteEntity)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): Flow<MutableList<NoteEntity>>

    @Query("SELECT * FROM user_table where kategori = :key")
    fun getDataByKategori(key: String): Flow<MutableList<NoteEntity>>

}