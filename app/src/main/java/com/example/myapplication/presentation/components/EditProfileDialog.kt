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
import com.example.myapplication.data.UserData

@Composable
fun EditProfileDialog(
    userData: UserData,
    onDismiss: () -> Unit,
    onSave: (String, String) -> Unit
) {
    var name by remember { mutableStateOf(userData.name) }
    var location by remember { mutableStateOf(userData.location) }
    var showLocationPicker by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Editar Perfil",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Campo de nombre
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre completo") },
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = "Nombre")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                // Campo de ubicación
                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    label = { Text("Ubicación") },
                    leadingIcon = {
                        Icon(Icons.Default.LocationOn, contentDescription = "Ubicación")
                    },
                    trailingIcon = {
                        IconButton(onClick = { showLocationPicker = true }) {
                            Icon(Icons.Default.MyLocation, contentDescription = "Mi ubicación")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    placeholder = { Text("Ej: Ventanilla, Callao") }
                )

                // Opciones de ubicación rápida
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = "Ubicaciones rápidas:",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf(
                                "Ventanilla, Callao",
                                "Callao",
                                "Lima",
                                "Perú"
                            ).forEach { quickLocation ->
                                AssistChip(
                                    onClick = { location = quickLocation },
                                    label = { Text(quickLocation) }
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank() && location.isNotBlank()) {
                        onSave(name.trim(), location.trim())
                        onDismiss()
                    }
                },
                enabled = name.isNotBlank() && location.isNotBlank()
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )

    // Dialog para seleccionar ubicación
    if (showLocationPicker) {
        LocationPickerDialog(
            onDismiss = { showLocationPicker = false },
            onLocationSelected = { selectedLocation ->
                location = selectedLocation
                showLocationPicker = false
            }
        )
    }
}

@Composable
fun LocationPickerDialog(
    onDismiss: () -> Unit,
    onLocationSelected: (String) -> Unit
) {
    val commonLocations = listOf(
        "Ventanilla, Callao",
        "Callao",
        "Lima",
        "Arequipa",
        "Cusco",
        "Trujillo",
        "Chiclayo",
        "Piura",
        "Iquitos",
        "Huancayo",
        "Tacna",
        "Ica"
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Seleccionar Ubicación",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Ubicaciones populares en Perú:",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(12.dp))
                
                commonLocations.chunked(2).forEach { rowLocations ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        rowLocations.forEach { location ->
                            AssistChip(
                                onClick = { onLocationSelected(location) },
                                label = { Text(location) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
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
