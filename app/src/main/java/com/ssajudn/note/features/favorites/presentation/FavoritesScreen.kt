package com.ssajudn.note.features.favorites.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.StickyNote2
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ssajudn.note.features.core.presentation.EmptyListScreen
import com.ssajudn.note.features.core.presentation.MainViewModel
import com.ssajudn.note.features.core.presentation.undoDeletedNote
import com.ssajudn.note.features.core.ui.theme.ubuntuFontFamily
import com.ssajudn.note.features.notes.presentation.components.LoadingAndErrorScreen
import com.ssajudn.note.features.notes.presentation.components.NoteList
import com.ssajudn.note.util.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    onEditClickNote: (Int) -> Unit,
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val response by viewModel.response.collectAsStateWithLifecycle()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(text = "Favorites", fontFamily = ubuntuFontFamily)
                },
            )
        }
    ) { contentPadding ->
        AnimatedContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            targetState = response,
            label = "animated content",
            transitionSpec = {
                fadeIn(
                    animationSpec = tween(
                        30,
                        easing = LinearEasing
                    )
                ) togetherWith fadeOut(
                    animationSpec = tween(
                        30,
                        easing = LinearEasing
                    )
                )
            }
        ) { result ->
            when (result) {
                is Response.Loading -> {
                    LoadingAndErrorScreen(label = "Loading...")
                }

                is Response.Success -> {
                    val favorites = result.data.filter { it.isBookmarked }
                    if (favorites.isEmpty()) {
                        EmptyListScreen()
                    } else {
                        NoteList(
                            notes = favorites,
                            onEditClickNote = onEditClickNote,
                            onUndoDeletedNote = {
                                undoDeletedNote(
                                    scope, snackbarHostState, viewModel
                                )
                            },
                        )
                    }
                }

                is Response.Error -> {
                    val msg = result.error.message ?: "Something went wrong"

                    LoadingAndErrorScreen(label = msg)
                }

                else -> Unit
            }
        }

    }
}