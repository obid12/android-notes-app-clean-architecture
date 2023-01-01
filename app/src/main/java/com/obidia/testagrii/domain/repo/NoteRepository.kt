package com.obidia.testagrii.domain.repo

import com.obidia.testagrii.domain.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun readAllData(): Flow<MutableList<NoteEntity>>

    fun getDataByKategori(kategori: String): Flow<MutableList<NoteEntity>>

    suspend fun addUser(note: NoteEntity)

    suspend fun updateUser(note: NoteEntity)

    suspend fun deleteUser(note: NoteEntity)

    suspend fun deleteAllUsers()

}