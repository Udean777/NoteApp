package com.ssajudn.note.features.notes.domain.use_cases

import com.ssajudn.note.features.notes.domain.NoteRepository
import com.ssajudn.note.features.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNotes @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getAllNotes()
    }
}