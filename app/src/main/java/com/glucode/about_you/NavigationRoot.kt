package com.glucode.about_you

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.glucode.about_you.about.presentation.composable.AboutScreenRoot
import com.glucode.about_you.engineers.presentation.engineers.composable.EngineerScreenRoot

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "engineer"
    ) {
        composable("engineer") {
            EngineerScreenRoot(
                onNavigatingToAbout = { navArgument ->
                    navController.navigate("about?name=$navArgument")
                }
            )
        }

        composable("about?name={name}", arguments = listOf(
            navArgument("name") {
                type = NavType.StringType
                nullable = true
            }
        )) {
            AboutScreenRoot(
                onNavigateBack = {
                    navController.navigate("engineer")
                }
            )
        }
    }
}