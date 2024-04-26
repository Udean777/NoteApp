package com.ssajudn.note.features.notes.domain.use_cases

import com.ssajudn.note.features.notes.domain.NoteRepository
import com.ssajudn.note.features.notes.domain.model.Note
import javax.inject.Inject

class UpdateNote @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) = repository.updateNote(note)
}