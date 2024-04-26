package com.ssajudn.note.features.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ssajudn.note.features.notes.domain.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = true)
abstract class LocalDB: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}