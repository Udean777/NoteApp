package com.ssajudn.note.features.notes.domain

import com.ssajudn.note.features.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNoteById(id: Int): Note

    fun getAllNotes(): Flow<List<Note>>
}