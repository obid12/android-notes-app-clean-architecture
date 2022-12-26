package com.obidia.testagrii.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val acktivitas: String,
    val detail: String,
    val kategori: String,
    val selesai: Boolean
) : Parcelable
