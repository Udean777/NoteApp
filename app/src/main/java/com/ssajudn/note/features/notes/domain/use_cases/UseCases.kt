package com.ssajudn.note.features.notes.domain.use_cases

data class UseCases(
    val insertNote: InsertNote,
    val updateNote: UpdateNote,
    val deleteNote: DeleteNote,
    val getNoteById: GetNoteById,
    val getAllNotes: GetAllNotes
)
