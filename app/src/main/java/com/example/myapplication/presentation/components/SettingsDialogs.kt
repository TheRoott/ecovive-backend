package com.example.myapplication.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.*

@Composable
fun LanguageSettingsDialog(
    currentLanguage: String,
    onDismiss: () -> Unit,
    onLanguageSelected: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Seleccionar Idioma",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                getLanguageOptions().forEach { language ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (language.code == currentLanguage) 
                                MaterialTheme.colorScheme.primaryContainer 
                            else 
                                MaterialTheme.colorScheme.surface
                        ),
                        onClick = { onLanguageSelected(language.code) }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = language.flag,
                                fontSize = 24.sp
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = language.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (language.code == currentLanguage) 
                                        MaterialTheme.colorScheme.onPrimaryContainer 
                                    else 
                                        MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = getLanguageDescription(language.code),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (language.code == currentLanguage) 
                                        MaterialTheme.colorScheme.onPrimaryContainer 
                                    else 
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            if (language.code == currentLanguage) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Seleccionado",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cerrar")
            }
        }
    )
}

@Composable
fun ThemeSettingsDialog(
    currentTheme: String,
    onDismiss: () -> Unit,
    onThemeSelected: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Seleccionar Tema",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                getThemeOptions().forEach { theme ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (theme.code == currentTheme) 
                                MaterialTheme.colorScheme.primaryContainer 
                            else 
                                MaterialTheme.colorScheme.surface
                        ),
                        onClick = { onThemeSelected(theme.code) }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = theme.icon,
                                fontSize = 24.sp
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = theme.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (theme.code == currentTheme) 
                                        MaterialTheme.colorScheme.onPrimaryContainer 
                                    else 
                                        MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = getThemeDescription(theme.code),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (theme.code == currentTheme) 
                                        MaterialTheme.colorScheme.onPrimaryContainer 
                                    else 
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            if (theme.code == currentTheme) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Seleccionado",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cerrar")
            }
        }
    )
}

// Helper functions
fun getLanguageDescription(code: String): String = when (code) {
    AppSettings.LANGUAGE_SPANISH -> "Idioma principal de la app"
    AppSettings.LANGUAGE_ENGLISH -> "Main language of the app"
    AppSettings.LANGUAGE_PORTUGUESE -> "Idioma principal do aplicativo"
    else -> "Idioma de la aplicación"
}

fun getThemeDescription(code: String): String = when (code) {
    AppSettings.THEME_LIGHT -> "Interfaz clara y brillante"
    AppSettings.THEME_DARK -> "Interfaz oscura y elegante"
    AppSettings.THEME_SYSTEM -> "Sigue la configuración del sistema"
    else -> "Configuración de tema"
}
