package com.obidia.testagrii.data.module

import android.content.Context
import androidx.room.Room
import com.obidia.testagrii.data.database.NoteDatabase
import com.obidia.testagrii.data.repository.NoteRepositoryImplementation
import com.obidia.testagrii.domain.repo.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app, NoteDatabase::class.java,
        NoteDatabase.DB_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImplementation(db.noteDao())
    }
}