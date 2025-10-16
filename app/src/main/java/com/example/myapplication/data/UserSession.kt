package com.example.myapplication.data

import android.content.Context
import android.content.SharedPreferences

data class UserData(
    val name: String,
    val email: String,
    val location: String = "Ventanilla, Callao",
    val ecoPoints: Int = 0,
    val level: String = "Explorador 🌱",
    val reportsCount: Int = 0,
    val joinedDate: String = "Enero 2024",
    val isLoggedIn: Boolean = false
)

class UserSessionManager(private val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("ecovive_session", Context.MODE_PRIVATE)
    
    fun saveUserSession(userData: UserData) {
        prefs.edit().apply {
            putString("user_name", userData.name)
            putString("user_email", userData.email)
            putString("user_location", userData.location)
            putInt("user_eco_points", userData.ecoPoints)
            putString("user_level", userData.level)
            putInt("user_reports_count", userData.reportsCount)
            putString("user_joined_date", userData.joinedDate)
            putBoolean("user_is_logged_in", userData.isLoggedIn)
            apply()
        }
    }
    
    fun getUserSession(): UserData {
        return UserData(
            name = prefs.getString("user_name", "EcoGuardián") ?: "EcoGuardián",
            email = prefs.getString("user_email", "usuario@ecovive.com") ?: "usuario@ecovive.com",
            location = prefs.getString("user_location", "Ventanilla, Callao") ?: "Ventanilla, Callao",
            ecoPoints = prefs.getInt("user_eco_points", 1250),
            level = prefs.getString("user_level", "Guardián 🌎") ?: "Guardián 🌎",
            reportsCount = prefs.getInt("user_reports_count", 15),
            joinedDate = prefs.getString("user_joined_date", "Enero 2024") ?: "Enero 2024",
            isLoggedIn = prefs.getBoolean("user_is_logged_in", true)
        )
    }
    
    fun updateEcoPoints(points: Int) {
        val currentPoints = prefs.getInt("user_eco_points", 1250)
        prefs.edit().putInt("user_eco_points", currentPoints + points).apply()
    }
    
    fun updateReportsCount() {
        val currentCount = prefs.getInt("user_reports_count", 15)
        prefs.edit().putInt("user_reports_count", currentCount + 1).apply()
    }
    
    fun updateLevel(newLevel: String) {
        prefs.edit().putString("user_level", newLevel).apply()
    }
    
    fun updateName(newName: String) {
        prefs.edit().putString("user_name", newName).apply()
    }
    
    fun updateLocation(newLocation: String) {
        prefs.edit().putString("user_location", newLocation).apply()
    }
    
    fun logout() {
        prefs.edit().putBoolean("user_is_logged_in", false).apply()
    }
    
    fun isUserLoggedIn(): Boolean {
        return prefs.getBoolean("user_is_logged_in", false)
    }
}

