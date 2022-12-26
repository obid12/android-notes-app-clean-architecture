package com.obidia.testagrii.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.obidia.testagrii.data.entity.NoteEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(data: NoteEntity)

    @Update
    fun updateUser(data: NoteEntity)

    @Delete
    fun deleteUser(data: NoteEntity)

    @Query("DELETE FROM user_table")
    fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<MutableList<NoteEntity>>

    @Query("SELECT * FROM user_table where kategori = :key")
    fun getDataByKategori(key: String): LiveData<MutableList<NoteEntity>>

}