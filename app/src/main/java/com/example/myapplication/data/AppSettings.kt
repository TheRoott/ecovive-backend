package com.example.myapplication.data

import android.content.Context
import android.content.SharedPreferences

class AppSettings(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)

    // Idioma
    var language: String
        get() = prefs.getString("language", "es") ?: "es"
        set(value) = prefs.edit().putString("language", value).apply()

    // Tema
    var theme: String
        get() = prefs.getString("theme", "system") ?: "system"
        set(value) = prefs.edit().putString("theme", value).apply()

    // Ubicación habilitada
    var locationEnabled: Boolean
        get() = prefs.getBoolean("location_enabled", false)
        set(value) = prefs.edit().putBoolean("location_enabled", value).apply()

    companion object {
        const val LANGUAGE_SPANISH = "es"
        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_PORTUGUESE = "pt"
        
        const val THEME_LIGHT = "light"
        const val THEME_DARK = "dark"
        const val THEME_SYSTEM = "system"
    }
}

// Data class para configuración de idioma
data class LanguageOption(
    val code: String,
    val name: String,
    val flag: String
)

// Data class para configuración de tema
data class ThemeOption(
    val code: String,
    val name: String,
    val icon: String
)

// Helper functions
fun getLanguageOptions(): List<LanguageOption> = listOf(
    LanguageOption("es", "Español", "🇪🇸"),
    LanguageOption("en", "English", "🇺🇸"),
    LanguageOption("pt", "Português", "🇧🇷")
)

fun getThemeOptions(): List<ThemeOption> = listOf(
    ThemeOption("light", "Claro", "☀️"),
    ThemeOption("dark", "Oscuro", "🌙"),
    ThemeOption("system", "Automático", "⚙️")
)
