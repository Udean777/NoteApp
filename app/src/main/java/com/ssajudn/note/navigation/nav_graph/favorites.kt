package com.ssajudn.note.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ssajudn.note.features.favorites.presentation.FavoritesScreen
import com.ssajudn.note.navigation.Screen
import com.ssajudn.note.navigation.Tab

fun NavGraphBuilder.favorites(navController: NavController) {
    navigation(
        startDestination = Screen.FavoritesScreen.route,
        route = Tab.Favorites.route
    ) {
        composable(route = Screen.FavoritesScreen.route) {
            FavoritesScreen(onEditClickNote = { noteId ->
                navController.navigate(
                    route = "${Screen.AddEditNoteScreen.route}/$noteId"
                )
            }, navController = navController)
        }
    }
}