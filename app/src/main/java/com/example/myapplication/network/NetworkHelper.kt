package com.example.myapplication.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkHelper {
    companion object {
        suspend fun testConnection(): String {
            return withContext(Dispatchers.IO) {
                try {
                    val response = ApiService.api.getHome()
                    "✅ Conectado: ${response.message}"
                } catch (e: Exception) {
                    "❌ Error de conexión: ${e.message}"
                }
            }
        }
        
        suspend fun checkHealth(): String {
            return withContext(Dispatchers.IO) {
                try {
                    val response = ApiService.api.getHealth()
                    "✅ Salud: ${response.status}"
                } catch (e: Exception) {
                    "❌ Error de salud: ${e.message}"
                }
            }
        }
    }
}
