package com.example.myapplication.presentation.screens.rewards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardsScreen() {
    var currentEcoPoints by remember { mutableStateOf(1250) }
    var currentLevel by remember { mutableStateOf("Guardián 🌎") }
    var selectedTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header con nivel actual
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF4CAF50),
                                Color(0xFF8BC34A)
                            )
                        )
                    )
                    .padding(24.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "🏆 Tu Progreso Ecológico",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = currentLevel,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "$currentEcoPoints EcoPuntos",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Barra de progreso
                    LinearProgressIndicator(
                        progress = 0.75f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.surface
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "750 puntos para el siguiente nivel",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }

        // Pestañas
        TabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier.fillMaxWidth()
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("🎁 Recompensas") },
                icon = { Icon(Icons.Default.EmojiEvents, contentDescription = null) }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("🏅 Logros") },
                icon = { Icon(Icons.Default.Star, contentDescription = null) }
            )
        }

        if (selectedTab == 0) {
            // Recompensas disponibles
            Text(
                text = "Recompensas Disponibles",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                getAvailableRewards().forEach { reward ->
                    RewardItem(reward = reward, currentPoints = currentEcoPoints)
                }
            }

            Text(
                text = "Recompensas Locales en Ventanilla",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(getLocalRewards()) { reward ->
                    LocalRewardCard(reward = reward, currentPoints = currentEcoPoints)
                }
            }
        } else {
            // Logros y estadísticas
            Text(
                text = "Logros Desbloqueados",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                getUserAchievements().forEach { achievement ->
                    AchievementCard(achievement = achievement)
                }
            }

            Text(
                text = "Ranking Mensual - Ventanilla",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "🏆 Top 5 EcoGuerreros de Ventanilla",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        getMonthlyRanking().forEach { ranking ->
                            RankingItem(ranking = ranking)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RewardItem(reward: EcoReward, currentPoints: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = reward.icon,
                fontSize = 40.sp
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = reward.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = reward.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "${reward.ecoPoints} EcoPuntos",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            if (currentPoints >= reward.ecoPoints && reward.isAvailable) {
                Button(
                    onClick = { /* Handle redeem */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Canjear")
                }
            } else {
                Text(
                    text = if (!reward.isAvailable) "No disponible" else "Puntos insuficientes",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun LocalRewardCard(reward: EcoReward, currentPoints: Int) {
    Card(
        modifier = Modifier.width(200.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = reward.icon,
                fontSize = 32.sp
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = reward.title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = reward.location ?: "Ventanilla",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "${reward.ecoPoints} pts",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun AchievementCard(achievement: UserAchievement) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (achievement.isUnlocked) 
                MaterialTheme.colorScheme.primaryContainer 
            else 
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = achievement.icon,
                fontSize = 32.sp
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = achievement.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = if (achievement.isUnlocked) 
                        MaterialTheme.colorScheme.onPrimaryContainer 
                    else 
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = achievement.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (achievement.isUnlocked) 
                        MaterialTheme.colorScheme.onPrimaryContainer 
                    else 
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            if (achievement.isUnlocked) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Desbloqueado",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun RankingItem(ranking: MonthlyRanking) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = ranking.position.toString(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.width(30.dp)
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Text(
            text = ranking.emoji,
            fontSize = 24.sp
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = ranking.userName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "${ranking.points} EcoPuntos",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        if (ranking.position <= 3) {
            Text(
                text = "🏆",
                fontSize = 20.sp
            )
        }
    }
}

// Data classes
data class EcoReward(
    val id: String,
    val title: String,
    val description: String,
    val ecoPoints: Int,
    val category: RewardCategory,
    val location: String? = null,
    val isAvailable: Boolean = true,
    val icon: String = "🎁"
)

enum class RewardCategory {
    LOCAL_BUSINESS,
    ECO_PRODUCT,
    EXPERIENCE,
    DISCOUNT,
    DONATION
}

data class UserAchievement(
    val icon: String,
    val title: String,
    val description: String,
    val isUnlocked: Boolean
)

data class MonthlyRanking(
    val position: Int,
    val userName: String,
    val points: Int,
    val emoji: String
)

// Helper functions
fun getAvailableRewards(): List<EcoReward> = listOf(
    EcoReward("1", "Descuento 20% - EcoStore", "Descuento en productos ecológicos", 500, RewardCategory.DISCOUNT),
    EcoReward("2", "Kit de Plantas Nativas", "Semillas de plantas nativas del Perú", 800, RewardCategory.ECO_PRODUCT),
    EcoReward("3", "Visita Guiada - Reserva Nacional", "Tour ecológico en reserva natural", 1200, RewardCategory.EXPERIENCE),
    EcoReward("4", "Donación a ONG Ambiental", "Tu contribución ayuda al medio ambiente", 300, RewardCategory.DONATION)
)

fun getLocalRewards(): List<EcoReward> = listOf(
    EcoReward("5", "Café Orgánico", "Descuento en cafetería local", 200, RewardCategory.LOCAL_BUSINESS, "Café Verde Ventanilla"),
    EcoReward("6", "Productos de Limpieza Eco", "Kit de limpieza ecológica", 400, RewardCategory.ECO_PRODUCT, "EcoLimp Ventanilla"),
    EcoReward("7", "Taller de Reciclaje", "Aprende técnicas de reciclaje", 600, RewardCategory.EXPERIENCE, "Centro Comunal Ventanilla")
)

fun getUserAchievements(): List<UserAchievement> = listOf(
    UserAchievement("📸", "Primer Reporte", "Enviaste tu primer reporte ambiental", true),
    UserAchievement("🎯", "Objetivo Cumplido", "Completaste 10 reportes exitosos", true),
    UserAchievement("🌟", "Estrella Verde", "Obtuviste 1000 EcoPuntos", true),
    UserAchievement("🔥", "Racha de 7", "Envía reportes por 7 días seguidos", false),
    UserAchievement("🏆", "Campeón Local", "Alcanza el top 3 del ranking de Ventanilla", false)
)

fun getMonthlyRanking(): List<MonthlyRanking> = listOf(
    MonthlyRanking(1, "EcoGuerrero", 2450, "🥇"),
    MonthlyRanking(2, "VerdeVentanilla", 1890, "🥈"),
    MonthlyRanking(3, "EcoFernando", 1650, "🥉"),
    MonthlyRanking(4, "Tú", 1250, "👤"),
    MonthlyRanking(5, "EcoMaría", 980, "👤")
)