package com.obidia.testagrii.domain.use_case

import com.obidia.testagrii.domain.entity.InvalidNoteException
import com.obidia.testagrii.domain.entity.NoteEntity
import com.obidia.testagrii.domain.repo.NoteRepository
import javax.inject.Inject

class NoteUseCase @Inject constructor(private val repository: NoteRepository) {
    fun getData() = repository.readAllData()

    @Throws(InvalidNoteException::class)
    suspend fun updateData(noteEntity: NoteEntity) {
        if (noteEntity.acktivitas.isBlank()) {
            throw InvalidNoteException("Aktivitas belom ada")
        }

        if (noteEntity.kategori.isBlank()) {
            throw InvalidNoteException("Kategori belom ada")
        }

        if (noteEntity.detail.isBlank()) {
            throw InvalidNoteException("Detail belom ada")
        }
        repository.updateUser(noteEntity)
    }

    suspend fun deleteUser(noteEntity: NoteEntity) = repository.deleteUser(noteEntity)
    suspend fun addData(noteEntity: NoteEntity) {
        if (noteEntity.acktivitas.isBlank()) {
            throw InvalidNoteException("Aktivitas belom ada")
        }

        if (noteEntity.kategori.isBlank()) {
            throw InvalidNoteException("Kategori belom ada")
        }

        if (noteEntity.detail.isBlank()) {
            throw InvalidNoteException("Detail belom ada")
        }
        repository.addUser(noteEntity)
    }
}