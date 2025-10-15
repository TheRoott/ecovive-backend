# 🚀 PASOS EXACTOS PARA DESPLEGAR EN RAILWAY

## ✅ **Tu repositorio ya está listo:**
**https://github.com/therrot/ecovive-backend.git**

---

## 📋 **PASOS SÚPER FÁCILES:**

### **Paso 1: Crear cuenta en Railway** ⏱️ 2 minutos
1. Ve a: **https://railway.app/**
2. Haz clic en **"Sign Up"**
3. Selecciona **"Continue with GitHub"**
4. Autoriza Railway a acceder a tu GitHub
5. ¡Listo!

### **Paso 2: Crear nuevo proyecto** ⏱️ 1 minuto
1. En Railway, haz clic en **"New Project"**
2. Selecciona **"Deploy from GitHub repo"**
3. Busca y selecciona **"therrot/ecovive-backend"**
4. Haz clic en **"Deploy Now"**

### **Paso 3: Configurar base de datos** ⏱️ 1 minuto
1. En tu proyecto Railway, haz clic en **"+"** (Add Service)
2. Selecciona **"Database"**
3. Elige **"PostgreSQL"**
4. Railway creará automáticamente la base de datos
5. ¡Listo!

### **Paso 4: Configurar variables de entorno** ⏱️ 2 minutos
1. Haz clic en tu servicio **"ecovive-backend"** (no en la base de datos)
2. Ve a la pestaña **"Variables"**
3. Agrega estas variables:

```
JWT_SECRET = ecovive_jwt_secret_key_2024_very_secure_256_bits
MAIL_USERNAME = tu_email@gmail.com
MAIL_PASSWORD = tu_password_de_app
```

**Nota**: `DATABASE_URL` se configura automáticamente

### **Paso 5: ¡Desplegar!** ⏱️ 5 minutos
1. Railway detectará automáticamente que es un proyecto Java/Maven
2. Comenzará a compilar automáticamente
3. Te mostrará los logs en tiempo real
4. Cuando termine, te dará una URL como: `https://ecovive-backend-production.up.railway.app`

---

## 🎉 **¡TU BACKEND ESTARÁ FUNCIONANDO!**

### **URLs de tu API:**
- **API Principal**: `https://tu-app.railway.app/api`
- **Documentación**: `https://tu-app.railway.app/api/swagger-ui.html`
- **Health Check**: `https://tu-app.railway.app/api/actuator/health`

### **Para conectar tu app móvil:**
Cambia en tu app Android:
```kotlin
// De esto:
const val BASE_URL = "http://localhost:8080/api"

// A esto (con tu URL real de Railway):
const val BASE_URL = "https://tu-app.railway.app/api"
```

---

## 💡 **TIPS IMPORTANTES:**

### **✅ Si algo no funciona:**
1. **Revisa los logs** en Railway dashboard
2. **Verifica las variables** de entorno
3. **Asegúrate** de que la base de datos esté conectada
4. **Espera** unos minutos para que compile completamente

### **✅ Para actualizar tu código:**
1. Haz cambios en tu código local
2. Ejecuta: `git add . && git commit -m "Update" && git push`
3. Railway desplegará automáticamente los cambios

### **✅ Monitoreo:**
- Railway te dará logs en tiempo real
- Puedes ver métricas de uso
- Recibirás alertas si hay problemas

---

## 🎊 **¡RESUMEN!**

**En 10 minutos tendrás:**
- ✅ Backend funcionando en la nube
- ✅ Base de datos PostgreSQL
- ✅ HTTPS automático
- ✅ API lista para tu app móvil
- ✅ Documentación automática
- ✅ **TODO GRATIS**

**¡Ve a https://railway.app/ y empieza ahora!** 🚀
