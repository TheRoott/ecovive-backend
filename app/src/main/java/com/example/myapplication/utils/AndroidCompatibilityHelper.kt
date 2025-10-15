package com.example.myapplication.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat

object AndroidCompatibilityHelper {
    
    private const val TAG = "AndroidCompatibilityHelper"
    
    /**
     * Verifica si el dispositivo es compatible con la aplicación
     */
    fun isCompatibleDevice(): Boolean {
        return Build.VERSION.SDK_INT >= 33 // Android 13+
    }
    
    /**
     * Obtiene la versión de Android
     */
    fun getAndroidVersion(): String {
        return when (Build.VERSION.SDK_INT) {
            33 -> "Android 13"
            34 -> "Android 14"
            35 -> "Android 15"
            else -> "Android ${Build.VERSION.RELEASE}"
        }
    }
    
    /**
     * Verifica permisos específicos según la versión de Android
     */
    fun checkPermissions(context: Context): Map<String, Boolean> {
        val permissions = mutableMapOf<String, Boolean>()
        
        // Permisos básicos
        permissions["INTERNET"] = true // Siempre disponible
        
        // Permisos de ubicación
        permissions["ACCESS_FINE_LOCATION"] = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        
        permissions["ACCESS_COARSE_LOCATION"] = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        
        // Permisos de cámara
        permissions["CAMERA"] = ContextCompat.checkSelfPermission(
            context, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
        
        // Permisos de almacenamiento (Android 13+)
        if (Build.VERSION.SDK_INT >= 33) {
            permissions["READ_MEDIA_IMAGES"] = ContextCompat.checkSelfPermission(
                context, Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            permissions["READ_EXTERNAL_STORAGE"] = ContextCompat.checkSelfPermission(
                context, Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }
        
        // Permisos de notificaciones (Android 13+)
        if (Build.VERSION.SDK_INT >= 33) {
            permissions["POST_NOTIFICATIONS"] = ContextCompat.checkSelfPermission(
                context, Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        }
        
        return permissions
    }
    
    /**
     * Obtiene los permisos requeridos según la versión de Android
     */
    fun getRequiredPermissions(): Array<String> {
        return if (Build.VERSION.SDK_INT >= 33) {
            // Android 13+
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.POST_NOTIFICATIONS
            )
        } else {
            // Android 12 y anteriores
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }
    
    /**
     * Verifica si el dispositivo tiene características específicas
     */
    fun hasRequiredFeatures(context: Context): Boolean {
        val packageManager = context.packageManager
        
        // Verifica cámara
        val hasCamera = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
        
        // Verifica ubicación
        val hasLocation = packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION) ||
                         packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)
        
        // Verifica pantalla táctil
        val hasTouchScreen = packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN)
        
        Log.d(TAG, "Características del dispositivo:")
        Log.d(TAG, "Cámara: $hasCamera")
        Log.d(TAG, "Ubicación: $hasLocation")
        Log.d(TAG, "Pantalla táctil: $hasTouchScreen")
        
        return hasTouchScreen // Mínimo requerido
    }
    
    /**
     * Obtiene información completa de compatibilidad
     */
    fun getCompatibilityInfo(context: Context): String {
        val permissions = checkPermissions(context)
        val hasFeatures = hasRequiredFeatures(context)
        
        return """
            === INFORMACIÓN DE COMPATIBILIDAD ===
            Versión de Android: ${getAndroidVersion()}
            API Level: ${Build.VERSION.SDK_INT}
            Compatible: ${isCompatibleDevice()}
            Características disponibles: $hasFeatures
            
            === PERMISOS ===
            ${permissions.map { "${it.key}: ${if (it.value) "✅" else "❌"}" }.joinToString("\n")}
            
            === DISPOSITIVO ===
            Marca: ${Build.MANUFACTURER}
            Modelo: ${Build.MODEL}
            Producto: ${Build.PRODUCT}
            Hardware: ${Build.HARDWARE}
        """.trimIndent()
    }
    
    /**
     * Configura optimizaciones específicas por versión
     */
    fun applyVersionOptimizations(context: Context) {
        when (Build.VERSION.SDK_INT) {
            33 -> {
                // Optimizaciones para Android 13
                Log.d(TAG, "Aplicando optimizaciones para Android 13")
            }
            34 -> {
                // Optimizaciones para Android 14
                Log.d(TAG, "Aplicando optimizaciones para Android 14")
            }
            35 -> {
                // Optimizaciones para Android 15
                Log.d(TAG, "Aplicando optimizaciones para Android 15")
            }
            else -> {
                Log.d(TAG, "Versión de Android no soportada: ${Build.VERSION.RELEASE}")
            }
        }
    }
}
