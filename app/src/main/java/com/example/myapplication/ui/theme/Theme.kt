package com.example.myapplication.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = EcoGreenDark80,
    onPrimary = NatureWhiteDark,
    primaryContainer = EcoGreenDark,
    onPrimaryContainer = EcoGreenLight,
    
    secondary = OceanBlueDark80,
    onSecondary = NatureWhiteDark,
    secondaryContainer = OceanBlueDark,
    onSecondaryContainer = OceanBlueLight,
    
    tertiary = EarthBrownDark,
    onTertiary = NatureWhiteDark,
    tertiaryContainer = EarthBrown,
    onTertiaryContainer = NatureWhite,
    
    background = NatureWhiteDark,
    onBackground = NatureWhite,
    surface = Color(0xFF1E1E1E),
    onSurface = NatureWhite,
    surfaceVariant = Color(0xFF2E2E2E),
    onSurfaceVariant = Color(0xFFB0B0B0),
    
    error = ErrorRed,
    onError = NatureWhite,
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    
    outline = Color(0xFF5A5A5A),
    outlineVariant = Color(0xFF3A3A3A),
    scrim = Color(0xFF000000)
)

private val LightColorScheme = lightColorScheme(
    primary = EcoGreenPrimary,
    onPrimary = NatureWhite,
    primaryContainer = LeafGreen,
    onPrimaryContainer = EcoGreenDark,
    
    secondary = OceanBluePrimary,
    onSecondary = NatureWhite,
    secondaryContainer = SkyBlue,
    onSecondaryContainer = OceanBlueDark,
    
    tertiary = EarthBrown,
    onTertiary = NatureWhite,
    tertiaryContainer = Color(0xFFFFE0B2),
    onTertiaryContainer = Color(0xFF3E2723),
    
    background = NatureWhite,
    onBackground = Color(0xFF1C1B1F),
    surface = NatureWhite,
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = Color(0xFF49454F),
    
    error = ErrorRed,
    onError = NatureWhite,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    
    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFCAC4D0),
    scrim = Color(0xFF000000)
)

@Composable
fun EcoViveTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Deshabilitado para mantener colores personalizados
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = EcoViveTypography,
        content = content
    )
}

// Mantener compatibilidad con el nombre anterior
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    EcoViveTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
        content = content
    )
}