package com.ssajudn.note.navigation.nav_graph

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.ssajudn.note.features.notes.presentation.AddEditNoteScreen
import com.ssajudn.note.features.notes.presentation.NoteScreen
import com.ssajudn.note.navigation.Screen
import com.ssajudn.note.navigation.Tab

fun NavGraphBuilder.notes(navController: NavController) {
    navigation(
        startDestination = Screen.NoteScreen.route,
        route = Tab.Notes.route
    ) {
        composable(
            route = Screen.NoteScreen.route
        ) {
            NoteScreen(
                onAddNoteClick = { noteId ->
                    navController.navigate(
                        route = "${Screen.AddEditNoteScreen.route}/$noteId"
                    )
                },
                onEditClickNote = { noteId ->
                    navController.navigate(
                        route = "${Screen.AddEditNoteScreen.route}/$noteId"
                    )
                },
            )
        }

        composable(
            route = "${Screen.AddEditNoteScreen.route}/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType }),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(
                        300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(
                        300,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        ) { entry ->
            entry.arguments?.getInt("noteId").let { noteId ->
                AddEditNoteScreen(noteId = noteId!!, navController = navController)
            }
        }
    }
}