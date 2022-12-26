package com.obidia.testagrii.presentation.listnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.obidia.testagrii.data.database.NoteDatabase
import com.obidia.testagrii.data.entity.NoteEntity
import com.obidia.testagrii.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {


    private val noteDao = NoteDatabase.getDatabase(application).noteDao()
    private val repository = NoteRepository(noteDao)
    val readAllData: LiveData<MutableList<NoteEntity>> = repository.readAllData
    private val _selesai = MutableLiveData<Boolean>()
    val selesai get() = _selesai

    fun setSelesai(data: Boolean) {
        _selesai.value = data
    }



    fun addUser(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(noteEntity)
        }
    }

    fun updateUser(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(noteEntity)
        }
    }

    fun deleteUser(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(noteEntity)
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
        }
    }

}