package com.example.myapplication

import android.app.Application
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
// import dagger.hilt.android.HiltAndroidApp

// @HiltAndroidApp
class EcoViveApplication : MultiDexApplication() {
    
    override fun onCreate() {
        super.onCreate()
        
        // Inicialización básica de la aplicación
        // MultiDex ya está configurado por la clase padre
    }
    
    override fun attachBaseContext(base: android.content.Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
