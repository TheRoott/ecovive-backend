package com.example.myapplication.presentation.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

data class EcoTip(
    val id: Int,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val category: String,
    val points: Int = 10
)

@Composable
fun EcoTipsFloatingWindow(
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedTip by remember { mutableStateOf<EcoTip?>(null) }
    
    val ecoTips = remember {
        listOf(
            EcoTip(
                id = 1,
                title = "Reduce el uso de bolsas plásticas",
                description = "En Lima se consumen millones de bolsas plásticas diariamente. Usa bolsas reutilizables de tela o papel cuando vayas al mercado.",
                icon = Icons.Default.ShoppingBag,
                category = "Basura",
                points = 15
            ),
            EcoTip(
                id = 2,
                title = "Ahorra agua en el hogar",
                description = "Lima tiene problemas de escasez de agua. Cierra el grifo mientras te lavas los dientes y usa la ducha por máximo 5 minutos.",
                icon = Icons.Default.WaterDrop,
                category = "Agua",
                points = 20
            ),
            EcoTip(
                id = 3,
                title = "Usa transporte público",
                description = "El transporte público en Lima reduce la contaminación del aire. Prefiere el Metropolitano, Metro o buses cuando sea posible.",
                icon = Icons.Default.DirectionsBus,
                category = "Transporte",
                points = 25
            ),
            EcoTip(
                id = 4,
                title = "Separa tus residuos",
                description = "Separa orgánicos, reciclables y no reciclables. En Lima hay puntos de reciclaje en varios distritos.",
                icon = Icons.Default.Recycling,
                category = "Reciclaje",
                points = 18
            ),
            EcoTip(
                id = 5,
                title = "Planta un árbol",
                description = "Los árboles en Lima ayudan a combatir la contaminación del aire. Participa en campañas de reforestación.",
                icon = Icons.Default.Park,
                category = "Vegetación",
                points = 30
            ),
            EcoTip(
                id = 6,
                title = "Reduce el consumo de energía",
                description = "Usa bombillas LED y desconecta aparatos que no uses. La energía en Lima proviene principalmente de hidroeléctricas.",
                icon = Icons.Default.Eco,
                category = "Energía",
                points = 12
            ),
            EcoTip(
                id = 7,
                title = "Evita el uso de aerosoles",
                description = "Los aerosoles contribuyen al smog en Lima. Usa alternativas naturales para la limpieza del hogar.",
                icon = Icons.Default.CleaningServices,
                category = "Aire",
                points = 16
            ),
            EcoTip(
                id = 8,
                title = "Compra productos locales",
                description = "Prefiere productos de la sierra y selva peruana. Reduce la huella de carbono del transporte.",
                icon = Icons.Default.Store,
                category = "Consumo",
                points = 14
            )
        )
    }
    
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Ventana flotante
        AnimatedVisibility(
            visible = isExpanded,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(300, easing = EaseOutQuart)
            ) + fadeIn(animationSpec = tween(300)),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(300, easing = EaseInQuart)
            ) + fadeOut(animationSpec = tween(300)),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .zIndex(1f)
        ) {
            Card(
                modifier = Modifier
                    .width(320.dp)
                    .height(500.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Header
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "🌱 Tips Eco Lima",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        IconButton(
                            onClick = { isExpanded = false }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Cerrar",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                    
                    Divider()
                    
                    // Lista de tips
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(ecoTips) { tip ->
                            EcoTipCard(
                                tip = tip,
                                onClick = { selectedTip = tip }
                            )
                        }
                    }
                }
            }
        }
        
        // Botón flotante
        FloatingActionButton(
            onClick = { isExpanded = !isExpanded },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon(
                imageVector = if (isExpanded) Icons.Default.Close else Icons.Default.Lightbulb,
                contentDescription = "Tips Eco"
            )
        }
        
        // Dialog para mostrar tip detallado
        selectedTip?.let { tip ->
            EcoTipDetailDialog(
                tip = tip,
                onDismiss = { selectedTip = null }
            )
        }
    }
}

@Composable
fun EcoTipCard(
    tip: EcoTip,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = tip.icon,
                    contentDescription = tip.category,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Contenido
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = tip.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = tip.category,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "+${tip.points} pts",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Ver más",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun EcoTipDetailDialog(
    tip: EcoTip,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = tip.icon,
                    contentDescription = tip.category,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = tip.title,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        text = {
            Column {
                Text(
                    text = tip.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Category,
                        contentDescription = "Categoría",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Categoría: ${tip.category}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Puntos",
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "+${tip.points} puntos",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Aplicar Tip")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cerrar")
            }
        }
    )
}
