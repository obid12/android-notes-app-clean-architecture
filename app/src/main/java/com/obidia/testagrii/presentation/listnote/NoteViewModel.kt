package com.obidia.testagrii.presentation.listnote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obidia.testagrii.domain.entity.InvalidNoteException
import com.obidia.testagrii.domain.entity.NoteEntity
import com.obidia.testagrii.domain.use_case.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val useCase: NoteUseCase
) :
    ViewModel() {

    fun readAllData() = useCase.getData()

    private val _selesai = MutableLiveData<Boolean>()

    fun setSelesai(data: Boolean) {
        _selesai.value = data
    }

    private val _aktivitas = MutableLiveData<String>()

    fun setAktivitas(data: String) {
        _aktivitas.value = data
    }

    private val _kategori = MutableLiveData<String>()

    fun setKategori(data: String) {
        _kategori.value = data
    }

    private val _detail = MutableLiveData<String>()

    fun setDetail(data: String) {
        _detail.value = data
    }

    private val _color = MutableLiveData<Int>()

    fun setColor(data: Int) {
        _color.value = data
    }

    private val _id = MutableLiveData<Int>()

    fun setId(data: Int) {
        _id.value = data
    }


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow get() = _eventFlow


    fun addUser() {
        val noteEntity =
            _selesai.value?.let { selesai ->
                _color.value?.let { color ->
                    NoteEntity(
                        0,
                        _aktivitas.value.toString(),
                        _detail.value.toString(),
                        _kategori.value.toString(),
                        selesai,
                        color
                    )
                }
            }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                noteEntity?.let { useCase.addData(it) }
                _eventFlow.emit(UiEvent.SaveNote)
            } catch (e: InvalidNoteException) {
                _eventFlow.emit(
                    UiEvent.ShowSnackbar(
                        message = e.message ?: "Couldn't save note"
                    )
                )
            }
        }
    }

    fun updateUser() {
        val noteEntity =
            _selesai.value?.let { selesai ->
                _color.value?.let { color ->
                    _id.value?.let { id ->
                        NoteEntity(
                            id,
                            _aktivitas.value.toString(),
                            _detail.value.toString(),
                            _kategori.value.toString(),
                            selesai,
                            color
                        )
                    }
                }
            }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                noteEntity?.let { useCase.updateData(it) }
                _eventFlow.emit(UiEvent.SaveNote)
            } catch (e: InvalidNoteException) {
                _eventFlow.emit(
                    UiEvent.ShowSnackbar(
                        message = e.message ?: "Couldn't save note"
                    )
                )
            }

        }
    }

    fun deleteUser(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.deleteUser(noteEntity)
        }
    }


}

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
    object SaveNote : UiEvent()
}