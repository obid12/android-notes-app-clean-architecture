package com.obidia.testagrii.domain.entity

import android.graphics.Color
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
@Entity(tableName = "user_table")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val acktivitas: String,
    val detail: String,
    val kategori: String,
    val selesai: Boolean,
    val color: Int,
) : Parcelable

class InvalidNoteException(message: String) : Exception(message)
