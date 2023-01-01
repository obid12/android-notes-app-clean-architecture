package com.obidia.testagrii.data.repository

import com.obidia.testagrii.data.database.NoteDao
import com.obidia.testagrii.domain.entity.NoteEntity
import com.obidia.testagrii.domain.repo.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImplementation @Inject constructor(private val noteDao: NoteDao) :
    NoteRepository {
    override fun readAllData(): Flow<MutableList<NoteEntity>> {
        return noteDao.readAllData()
    }

    override fun getDataByKategori(kategori: String): Flow<MutableList<NoteEntity>> {
        return noteDao.getDataByKategori(kategori)
    }

    override suspend fun addUser(note: NoteEntity) {
        return noteDao.addUser(note)
    }

    override suspend fun updateUser(note: NoteEntity) {
        return noteDao.updateUser(note)
    }

    override suspend fun deleteUser(note: NoteEntity) {
        return noteDao.deleteUser(note)
    }

    override suspend fun deleteAllUsers() {
        return noteDao.deleteAllUsers()
    }
}