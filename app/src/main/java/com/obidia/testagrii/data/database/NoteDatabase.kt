package com.obidia.testagrii.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.obidia.testagrii.domain.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 3, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        const val DB_NAME = "notes_db"
    }

}