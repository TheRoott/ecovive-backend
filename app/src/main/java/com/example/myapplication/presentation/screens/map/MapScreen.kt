package com.example.myapplication.presentation.screens.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// import com.example.myapplication.BuildConfig // Temporalmente comentado
import com.example.myapplication.data.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen() {
    var selectedFilter by remember { mutableStateOf(ReportCategory.TRASH) }
    var showFilters by remember { mutableStateOf(false) }

    // Ubicación de Ventanilla, Callao
    val ventanillaLocation = LatLng(-11.8650, -77.1094)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(ventanillaLocation, 13f)
    }

    // Reportes de ejemplo con ubicaciones reales en Ventanilla
    val reports = remember {
        listOf(
            EnvironmentalReport(
                category = ReportCategory.TRASH,
                title = "Basura en el malecón",
                description = "Acumulación de residuos en el paseo marítimo",
                location = com.example.myapplication.utils.LocationData(-11.8650, -77.1094, "Malecón de Ventanilla"),
                status = ReportStatus.PENDING,
                ecoPoints = 10
            ),
            EnvironmentalReport(
                category = ReportCategory.POLLUTION,
                title = "Humo industrial",
                description = "Emisiones contaminantes de fábrica cercana",
                location = com.example.myapplication.utils.LocationData(-11.8700, -77.1050, "Zona Industrial Ventanilla"),
                status = ReportStatus.IN_PROGRESS,
                ecoPoints = 15
            ),
            EnvironmentalReport(
                category = ReportCategory.WATER_POLLUTION,
                title = "Contaminación en playa",
                description = "Aguas contaminadas en playa de Ventanilla",
                location = com.example.myapplication.utils.LocationData(-11.8600, -77.1100, "Playa Ventanilla"),
                status = ReportStatus.RESOLVED,
                ecoPoints = 25
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header con filtros
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "🗺️ Mapa de Reportes",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Ventanilla, Callao",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    
                    IconButton(
                        onClick = { showFilters = !showFilters }
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Filtros",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                
                if (showFilters) {
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(ReportCategory.values()) { category ->
                            FilterChip(
                                onClick = { selectedFilter = category },
                                label = { 
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(category.icon)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(category.title)
                                    }
                                },
                                selected = selectedFilter == category,
                                leadingIcon = if (selectedFilter == category) {
                                    { Icon(Icons.Default.Check, contentDescription = null) }
                                } else null
                            )
                        }
                    }
                }
            }
        }
        
        // Mapa visual con puntos de contaminación
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                // Fondo del mapa con gradiente
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF4CAF50), // Verde para áreas limpias
                                    Color(0xFF8BC34A),
                                    Color(0xFFFFEB3B), // Amarillo para áreas moderadas
                                    Color(0xFFFF9800), // Naranja para áreas contaminadas
                                    Color(0xFFF44336)  // Rojo para áreas muy contaminadas
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    // Título del mapa
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Icon(
                            Icons.Default.Map,
                            contentDescription = "Mapa de Contaminación",
                            modifier = Modifier.size(32.dp),
                            tint = Color.White
                        )
                        Text(
                            text = "🗺️ Mapa de Contaminación",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Ventanilla, Callao",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                    }
                    
                    // Puntos de contaminación
                    reports.filter { it.category == selectedFilter }.forEach { report ->
                        val intensity = when (report.category) {
                            ReportCategory.TRASH -> 0.8f
                            ReportCategory.WATER_POLLUTION -> 0.6f
                            ReportCategory.AIR_POLLUTION -> 0.9f
                            ReportCategory.POLLUTION -> 0.4f
                            ReportCategory.DEFORESTATION -> 0.7f
                            ReportCategory.WILDLIFE -> 0.3f
                            ReportCategory.OTHER -> 0.2f
                        }
                        
                        val pointColor = when {
                            intensity >= 0.8f -> Color(0xFFFF0000) // Rojo intenso
                            intensity >= 0.6f -> Color(0xFFFF6600) // Rojo-naranja
                            intensity >= 0.4f -> Color(0xFFFF9900) // Naranja
                            else -> Color(0xFFFFCC00) // Amarillo
                        }
                        
                        // Convertir coordenadas a posición en pantalla
                        val screenX = ((report.location.latitude + 12.0) * 200).coerceIn(16.0, 280.0)
                        val screenY = ((report.location.longitude + 78.0) * 200).coerceIn(16.0, 200.0)
                        
                        Box(
                            modifier = Modifier
                                .offset(
                                    x = screenX.dp,
                                    y = screenY.dp
                                )
                                .size((intensity * 20 + 10).dp)
                                .clip(CircleShape)
                                .background(pointColor)
                                .clickable {
                                    // Mostrar detalles del reporte
                                }
                        ) {
                            // Indicador de intensidad
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        Color.Black.copy(alpha = 0.3f),
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${(intensity * 100).toInt()}%",
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
                
                // Leyenda de colores
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                        .background(
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Nivel de Contaminación:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(Color(0xFFFF0000), CircleShape)
                        )
                        Text(
                            text = " Alto (80-100%)",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(Color(0xFFFF6600), CircleShape)
                        )
                        Text(
                            text = " Medio (60-80%)",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(Color(0xFFFF9900), CircleShape)
                        )
                        Text(
                            text = " Bajo (40-60%)",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
        
        // Información de reportes filtrados
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "📍 Reportes de ${selectedFilter.title}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    reports.forEach { report ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = when (report.status) {
                                    ReportStatus.PENDING -> Color(0xFFFF9800).copy(alpha = 0.1f)
                                    ReportStatus.IN_PROGRESS -> Color(0xFF2196F3).copy(alpha = 0.1f)
                                    ReportStatus.RESOLVED -> Color(0xFF4CAF50).copy(alpha = 0.1f)
                                    ReportStatus.VERIFIED -> Color(0xFF9C27B0).copy(alpha = 0.1f)
                                }
                            )
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = report.category.icon,
                                    fontSize = 24.sp
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = report.title,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = report.location.address,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = "Estado: ${report.status.title}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = when (report.status) {
                                            ReportStatus.PENDING -> Color(0xFFFF9800)
                                            ReportStatus.IN_PROGRESS -> Color(0xFF2196F3)
                                            ReportStatus.RESOLVED -> Color(0xFF4CAF50)
                                            ReportStatus.VERIFIED -> Color(0xFF9C27B0)
                                        }
                                    )
                                }
                                Text(
                                    text = "${report.ecoPoints} pts",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        }
    }

@Composable
fun ReportItemCard(report: EnvironmentalReport) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = report.category.icon,
                fontSize = 24.sp
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = report.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = report.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Ubicación",
                        modifier = Modifier.size(12.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = report.location.address,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            AssistChip(
                onClick = { },
                label = { Text("Ver") },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = when (report.status) {
                        ReportStatus.PENDING -> MaterialTheme.colorScheme.errorContainer
                        ReportStatus.IN_PROGRESS -> MaterialTheme.colorScheme.primaryContainer
                        ReportStatus.RESOLVED -> MaterialTheme.colorScheme.tertiaryContainer
                        ReportStatus.VERIFIED -> MaterialTheme.colorScheme.secondaryContainer
                    }
                )
            )
        }
    }
}