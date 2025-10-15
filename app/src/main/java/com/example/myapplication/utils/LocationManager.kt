package com.example.myapplication.utils

import android.content.Context
import android.location.Geocoder
import java.io.IOException
import java.util.*

class LocationManager(private val context: Context) {
    
    fun getCurrentLocation(): LocationData? {
        return try {
            // Simular ubicación de Ventanilla, Callao para desarrollo
            LocationData(
                latitude = -11.9000,
                longitude = -77.1000,
                address = "Ventanilla, Callao, Perú"
            )
        } catch (e: Exception) {
            LocationData(
                latitude = -11.9000,
                longitude = -77.1000,
                address = "Ventanilla, Callao, Perú"
            )
        }
    }
    
    private fun getAddressFromCoordinates(latitude: Double, longitude: Double): String {
        return try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses?.isNotEmpty() == true) {
                addresses[0].getAddressLine(0) ?: "Ventanilla, Callao, Perú"
            } else {
                "Ventanilla, Callao, Perú"
            }
        } catch (e: IOException) {
            "Ventanilla, Callao, Perú"
        }
    }
}