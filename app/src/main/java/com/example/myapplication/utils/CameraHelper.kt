package com.example.myapplication.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.core.content.ContextCompat

object CameraHelper {
    
    fun isCameraAvailable(context: Context): Boolean {
        return try {
            val hasCamera = context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)
            val hasCameraAny = context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
            Log.d("CameraHelper", "FEATURE_CAMERA: $hasCamera, FEATURE_CAMERA_ANY: $hasCameraAny")
            hasCamera || hasCameraAny
        } catch (e: Exception) {
            Log.e("CameraHelper", "Error verificando cámara: ${e.message}")
            false
        }
    }
    
    fun isCameraPermissionGranted(context: Context): Boolean {
        return try {
            val granted = ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
            Log.d("CameraHelper", "Permiso de cámara: $granted")
            granted
        } catch (e: Exception) {
            Log.e("CameraHelper", "Error verificando permisos: ${e.message}")
            false
        }
    }
    
    fun hasStoragePermission(context: Context): Boolean {
        return try {
            val granted = ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
            Log.d("CameraHelper", "Permiso de almacenamiento: $granted")
            granted
        } catch (e: Exception) {
            Log.e("CameraHelper", "Error verificando permisos de almacenamiento: ${e.message}")
            false
        }
    }
    
    fun openAppSettings(context: Context) {
        try {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
            Log.d("CameraHelper", "Configuración de app abierta")
        } catch (e: Exception) {
            Log.e("CameraHelper", "Error al abrir configuración: ${e.message}")
            e.printStackTrace()
        }
    }
    
    fun getCameraErrorMessage(context: Context): String {
        return when {
            !isCameraAvailable(context) -> "Este dispositivo no tiene cámara disponible"
            !isCameraPermissionGranted(context) -> "Se necesita permiso de cámara para tomar fotos"
            !hasStoragePermission(context) -> "Se necesita permiso de almacenamiento para guardar fotos"
            else -> "Error desconocido con la cámara"
        }
    }
    
    fun logCameraStatus(context: Context) {
        Log.d("CameraHelper", "=== ESTADO DE LA CÁMARA ===")
        Log.d("CameraHelper", "Cámara disponible: ${isCameraAvailable(context)}")
        Log.d("CameraHelper", "Permiso de cámara: ${isCameraPermissionGranted(context)}")
        Log.d("CameraHelper", "Permiso de almacenamiento: ${hasStoragePermission(context)}")
        Log.d("CameraHelper", "Paquete de la app: ${context.packageName}")
        Log.d("CameraHelper", "==========================")
    }
    
    fun validateCameraSetup(context: Context): Boolean {
        return try {
            val cameraAvailable = isCameraAvailable(context)
            val cameraPermission = isCameraPermissionGranted(context)
            val storagePermission = hasStoragePermission(context)
            
            Log.d("CameraHelper", "Validación completa:")
            Log.d("CameraHelper", "- Cámara disponible: $cameraAvailable")
            Log.d("CameraHelper", "- Permiso cámara: $cameraPermission")
            Log.d("CameraHelper", "- Permiso almacenamiento: $storagePermission")
            
            // Para MIUI, a veces los permisos se reportan incorrectamente
            // pero la cámara funciona, así que seremos más permisivos
            val result = cameraAvailable && cameraPermission
            Log.d("CameraHelper", "Validación MIUI: $result")
            
            result
        } catch (e: Exception) {
            Log.e("CameraHelper", "Error en validación: ${e.message}")
            false
        }
    }
    
    fun checkMIUICompatibility(context: Context): Boolean {
        return try {
            val packageManager = context.packageManager
            val miuiInfo = packageManager.getApplicationInfo("com.miui.securitycenter", 0)
            Log.d("CameraHelper", "MIUI detectado: ${miuiInfo.packageName}")
            true
        } catch (e: Exception) {
            Log.d("CameraHelper", "No es MIUI o MIUI no detectado")
            false
        }
    }
}
