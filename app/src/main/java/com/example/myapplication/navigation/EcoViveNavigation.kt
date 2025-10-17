package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.presentation.screens.auth.SimpleLoginScreen
import com.example.myapplication.presentation.screens.auth.SimpleRegisterScreen
import com.example.myapplication.presentation.screens.main.MainScreen
import com.example.myapplication.presentation.screens.splash.SplashScreen
import com.example.myapplication.presentation.screens.test.TestConnectionScreen
import com.example.myapplication.presentation.screens.stats.StatsScreen
import com.example.myapplication.presentation.screens.about.AboutScreen

@Composable
fun EcoViveNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToAuth = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToMain = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
            )
        }
        
        composable(Screen.Login.route) {
            SimpleLoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onNavigateToMain = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Register.route) {
            SimpleRegisterScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onNavigateToMain = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Main.route) {
            MainScreen()
        }
        
        composable(Screen.TestConnection.route) {
            TestConnectionScreen()
        }
        
        composable(Screen.Stats.route) {
            StatsScreen()
        }
        
        composable(Screen.About.route) {
            AboutScreen()
        }
    }
}

