package com.example.myapplication.utils

import android.content.Context
import android.os.Build
import android.util.Log

object PocoX7ProHelper {
    
    private const val TAG = "PocoX7ProHelper"
    
    /**
     * Detecta si el dispositivo es un POCO X7 Pro o similar
     */
    fun isPocoX7Pro(): Boolean {
        val brand = Build.BRAND.lowercase()
        val model = Build.MODEL.lowercase()
        val manufacturer = Build.MANUFACTURER.lowercase()
        
        return (brand.contains("poco") || brand.contains("xiaomi") || 
                manufacturer.contains("xiaomi")) &&
               (model.contains("x7") || model.contains("vayu") || 
                model.contains("bomb") || model.contains("poco"))
    }
    
    /**
     * Configura el rendimiento específico para POCO X7 Pro
     */
    fun optimizeForPocoX7Pro(context: Context) {
        if (isPocoX7Pro()) {
            Log.d(TAG, "Optimizando para POCO X7 Pro")
        }
    }
    
    /**
     * Configura el tema específico para MIUI
     */
    fun configureMIUITheme(context: Context) {
        if (isPocoX7Pro()) {
            Log.d(TAG, "Configurando tema MIUI")
        }
    }
    
    /**
     * Obtiene información del dispositivo
     */
    fun getDeviceInfo(): String {
        return """
            Dispositivo: ${Build.MANUFACTURER} ${Build.MODEL}
            Android: ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})
            MIUI: ${isPocoX7Pro()}
        """.trimIndent()
    }
    
    /**
     * Verifica si el dispositivo tiene características específicas
     */
    fun hasRequiredFeatures(): Boolean {
        return Build.VERSION.SDK_INT >= 33 // Android 13+
    }
}
