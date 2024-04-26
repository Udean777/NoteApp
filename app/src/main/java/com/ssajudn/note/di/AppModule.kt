package com.ssajudn.note.di

import android.content.Context
import androidx.room.Room
import com.ssajudn.note.features.notes.data.LocalDB
import com.ssajudn.note.features.notes.data.NoteRepositoryImpl
import com.ssajudn.note.features.notes.domain.NoteRepository
import com.ssajudn.note.features.notes.domain.use_cases.DeleteNote
import com.ssajudn.note.features.notes.domain.use_cases.GetAllNotes
import com.ssajudn.note.features.notes.domain.use_cases.GetNoteById
import com.ssajudn.note.features.notes.domain.use_cases.InsertNote
import com.ssajudn.note.features.notes.domain.use_cases.UpdateNote
import com.ssajudn.note.features.notes.domain.use_cases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, LocalDB::class.java, "local_database")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideNoteRepository(db: LocalDB): NoteRepository {
        return NoteRepositoryImpl(dao = db.noteDao())
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: NoteRepository): UseCases {
        return UseCases(
            insertNote = InsertNote(repository),
            updateNote = UpdateNote(repository),
            deleteNote = DeleteNote(repository),
            getNoteById = GetNoteById(repository),
            getAllNotes = GetAllNotes(repository)
        )
    }
}