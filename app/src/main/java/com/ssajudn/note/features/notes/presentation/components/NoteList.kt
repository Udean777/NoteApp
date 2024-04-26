package com.ssajudn.note.features.notes.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ssajudn.note.features.notes.domain.model.Note

@Composable
fun NoteList(
    notes: List<Note>,
    onEditClickNote: (Int) -> Unit,
    onUndoDeletedNote: () -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        columns = StaggeredGridCells.Adaptive(160.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(notes) { note ->
            NoteCard(
                note = note,
                onEditClickNote = onEditClickNote,
                onUndoDeletedNote = { onUndoDeletedNote() })
        }
    }
}