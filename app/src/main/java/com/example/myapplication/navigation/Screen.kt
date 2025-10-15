package com.example.myapplication.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Main : Screen("main")
    
    // Pantallas principales dentro del Main
    object Home : Screen("home")
    object Report : Screen("report")
    object Map : Screen("map")
    object Rewards : Screen("rewards")
    object Profile : Screen("profile")
}

