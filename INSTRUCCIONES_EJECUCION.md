# 🌱 EcoVive Perú - Instrucciones de Ejecución

## 📱 **COMPATIBLE CON:**
- ✅ **Android 13** (API 33)
- ✅ **Android 14** (API 34) 
- ✅ **Android 15** (API 35)
- ✅ **POCO X7 Pro** y dispositivos MIUI
- ✅ **Todos los dispositivos Android modernos**

## 🚀 **PASOS RÁPIDOS PARA EJECUTAR EN ANDROID STUDIO**

### 1. **Abrir el Proyecto**
1. Abre **Android Studio**
2. **File** → **Open**
3. Selecciona la carpeta **alcatras**
4. Espera a que se complete la sincronización de Gradle

### 2. **Configurar Dispositivo**
#### Opción A: Emulador
1. **Tools** → **AVD Manager**
2. **Create Virtual Device**
3. Selecciona **Pixel 4** o similar
4. Descarga **API 24** o superior
5. **Finish**

#### Opción B: Dispositivo Físico
1. Habilita **Opciones de desarrollador** en tu Android
2. Activa **Depuración USB**
3. Conecta por USB y autoriza

### 3. **Ejecutar la Aplicación**
1. Selecciona tu dispositivo en la barra superior
2. Haz clic en **Run** (▶️) o presiona **Shift + F10**
3. ¡Listo! La app se instalará y ejecutará

## ✅ **FUNCIONALIDADES DISPONIBLES**

### **Funciona Sin Configuración Adicional:**
- ✅ **Splash Screen** animado
- ✅ **Login/Registro** completo
- ✅ **Dashboard** principal con estadísticas
- ✅ **Sistema de Reportes** (formulario)
- ✅ **Pantalla de Recompensas** y logros
- ✅ **Perfil de Usuario**
- ✅ **Navegación** entre pantallas
- ✅ **Tema personalizado** verde/azul

### **Requiere Configuración (Opcional):**
- ⚠️ **Mapa interactivo** (requiere Google Maps API Key)
- ⚠️ **Cámara** para fotos (requiere permisos)
- ⚠️ **GPS** para ubicación (requiere permisos)

## 🔧 **SOLUCIÓN DE PROBLEMAS**

### **Error: "Gradle sync failed"**
**Solución:**
1. **File** → **Invalidate Caches and Restart**
2. Selecciona **Invalidate and Restart**
3. Espera a que se reinicie

### **Error: "SDK location not found"**
**Solución:**
1. **File** → **Project Structure** → **SDK Location**
2. Verifica que el Android SDK esté configurado

### **Error: "OutOfMemoryError"**
**Solución:**
1. **Help** → **Edit Custom VM Options**
2. Agrega: `-Xmx4096m`
3. Reinicia Android Studio

### **La app se cierra al abrir**
**Solución:**
1. Ve a **Logcat** en Android Studio
2. Busca errores en rojo
3. Verifica que el dispositivo tenga Android 7.0+ (API 24+)

## 📱 **CONFIGURACIÓN OPCIONAL**

### **Para Habilitar Google Maps:**
1. Ve a [Google Cloud Console](https://console.cloud.google.com/)
2. Crea un proyecto y habilita **Maps SDK for Android**
3. Genera una **API Key**
4. Abre `local.properties` y agrega:
```properties
GOOGLE_MAPS_API_KEY=tu_api_key_aqui
```

### **Para Habilitar Cámara:**
1. En el dispositivo, ve a **Configuración** → **Apps** → **EcoVive Perú**
2. **Permisos** → **Cámara** → **Permitir**
3. **Ubicación** → **Permitir**

## 🎯 **ESTRUCTURA DE LA APLICACIÓN**

```
EcoVive Perú/
├── 🌟 Splash Screen (Bienvenida animada)
├── 🔐 Login/Register (Autenticación)
├── 🏠 Home (Dashboard principal)
├── 📸 Report (Sistema de reportes)
├── 🗺️ Map (Mapa de problemas)
├── 🏆 Rewards (Gamificación)
└── 👤 Profile (Perfil de usuario)
```

## 📊 **CARACTERÍSTICAS IMPLEMENTADAS**

### **🎨 Diseño Profesional**
- Paleta de colores verde/azul ambientalista
- Tipografía profesional
- Animaciones fluidas
- Tema claro y oscuro

### **🔐 Autenticación**
- Login y registro completos
- Validación de campos
- Animaciones de transición

### **🏠 Dashboard**
- Puntos ecológicos animados
- Estadísticas en tiempo real
- Acciones rápidas
- Noticias ambientales
- Consejos del día

### **📸 Sistema de Reportes**
- 6 categorías de problemas ambientales
- Formulario completo de descripción
- Sistema de recompensas (50 EcoPuntos)

### **🏆 Gamificación**
- Niveles: Explorador, Protector, Guardián
- Logros desbloqueables
- Ranking mensual
- Recompensas canjeables

### **👤 Perfil de Usuario**
- Estadísticas personales
- Logros destacados
- Configuración de cuenta

## 🌍 **ENFOQUE AMBIENTAL**

### **Para Ventanilla y Lima:**
- Contenido local y relevante
- Categorías específicas de problemas ambientales
- Mensajes motivacionales
- Noticias ambientales locales

### **Categorías de Reporte:**
- 🗑️ Basura en espacios públicos
- 🌫️ Contaminación del aire
- 🌳 Tala ilegal
- 💧 Contaminación del agua
- 🐾 Maltrato animal
- ☠️ Sustancias químicas

## 📞 **SI TIENES PROBLEMAS**

### **Verifica que tienes:**
- ✅ Android Studio 2023.1.1 o superior
- ✅ Android SDK API 33+ (Android 13+)
- ✅ Java 11 o superior
- ✅ Conexión a internet
- ✅ POCO X7 Pro con Android 13/14/15 (recomendado)

### **Logs útiles:**
- **Build Output**: Errores de compilación
- **Run**: Errores de ejecución
- **Logcat**: Errores en tiempo real

---

## 🎉 **¡LISTO PARA USAR!**

La aplicación **EcoVive Perú** está completamente funcional y lista para ejecutarse en Android Studio. Todas las pantallas principales funcionan sin configuración adicional, y puedes agregar funcionalidades avanzadas como mapas y cámara cuando estés listo.

**🌱 ¡Disfruta explorando la aplicación ambientalista más completa para Perú! 🌍**
