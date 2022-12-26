package com.obidia.testagrii.data.repository

import androidx.lifecycle.LiveData
import com.obidia.testagrii.data.database.NoteDao
import com.obidia.testagrii.data.entity.NoteEntity

class NoteRepository(private val noteDao: NoteDao) {

    val readAllData: LiveData<MutableList<NoteEntity>> = noteDao.readAllData()

    suspend fun getDataByKategori(kategori: String): LiveData<MutableList<NoteEntity>> {
        return noteDao.getDataByKategori(kategori)
    }

    suspend fun addUser(note: NoteEntity) {
        noteDao.addUser(note)
    }

    suspend fun updateUser(note: NoteEntity) {
        noteDao.updateUser(note)
    }

    suspend fun deleteUser(note: NoteEntity) {
        noteDao.deleteUser(note)
    }

    suspend fun deleteAllUsers() {
        noteDao.deleteAllUsers()
    }

}