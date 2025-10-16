@echo off
echo ========================================
echo DESPLIEGUE GRATIS EN RAILWAY
echo Backend EcoVive Perú - 100%% GRATIS
echo ========================================
echo.

echo [1/6] ✅ VERIFICANDO REQUISITOS:
echo.

echo Verificando Git...
git --version >nul 2>&1
if %errorlevel%==0 (
    echo    - Git: INSTALADO ✓
) else (
    echo    - Git: NO INSTALADO ✗
    echo    Descarga desde: https://git-scm.com/downloads
    pause
    exit /b 1
)

echo.
echo Verificando Node.js...
node --version >nul 2>&1
if %errorlevel%==0 (
    echo    - Node.js: INSTALADO ✓
) else (
    echo    - Node.js: NO INSTALADO ✗
    echo    Descarga desde: https://nodejs.org/
    pause
    exit /b 1
)

echo.
echo [2/6] ✅ CONFIGURANDO PROYECTO PARA RAILWAY:
echo.

echo Creando archivo railway.json...
echo {> railway.json
echo   "build": {>> railway.json
echo     "builder": "NIXPACKS">> railway.json
echo   },>> railway.json
echo   "deploy": {>> railway.json
echo     "startCommand": "java -jar target/ecovive-backend-1.0.0.jar",>> railway.json
echo     "healthcheckPath": "/api/actuator/health">> railway.json
echo   }>> railway.json
echo }>> railway.json
echo    - railway.json: CREADO ✓

echo.
echo Creando archivo .env.example...
echo DATABASE_URL=postgresql://postgres:password@localhost:5432/ecovive_db> .env.example
echo JWT_SECRET=ecovive_jwt_secret_key_2024_very_secure_256_bits>> .env.example
echo AWS_ACCESS_KEY_ID=your_access_key>> .env.example
echo AWS_SECRET_ACCESS_KEY=your_secret_key>> .env.example
echo MAIL_USERNAME=your_email@gmail.com>> .env.example
echo MAIL_PASSWORD=your_app_password>> .env.example
echo    - .env.example: CREADO ✓

echo.
echo Configurando application.yml para Railway...
cd backend
echo.
echo server:> src\main\resources\application-railway.yml
echo   port: ${PORT:8080}>> src\main\resources\application-railway.yml
echo.>> src\main\resources\application-railway.yml
echo spring:>> src\main\resources\application-railway.yml
echo   datasource:>> src\main\resources\application-railway.yml
echo     url: ${DATABASE_URL}>> src\main\resources\application-railway.yml
echo     username: ${DATABASE_USERNAME}>> src\main\resources\application-railway.yml
echo     password: ${DATABASE_PASSWORD}>> src\main\resources\application-railway.yml
echo   jpa:>> src\main\resources\application-railway.yml
echo     hibernate:>> src\main\resources\application-railway.yml
echo       ddl-auto: update>> src\main\resources\application-railway.yml
echo   security:>> src\main\resources\application-railway.yml
echo     jwt:>> src\main\resources\application-railway.yml
echo       secret: ${JWT_SECRET}>> src\main\resources\application-railway.yml
echo       expiration: 86400000>> src\main\resources\application-railway.yml
echo.>> src\main\resources\application-railway.yml
echo aws:>> src\main\resources\application-railway.yml
echo   s3:>> src\main\resources\application-railway.yml
echo     bucket-name: ecovive-peru-files>> src\main\resources\application-railway.yml
echo     region: us-east-1>> src\main\resources\application-railway.yml
echo     access-key: ${AWS_ACCESS_KEY_ID}>> src\main\resources\application-railway.yml
echo     secret-key: ${AWS_SECRET_ACCESS_KEY}>> src\main\resources\application-railway.yml
echo.>> src\main\resources\application-railway.yml
echo mail:>> src\main\resources\application-railway.yml
echo   host: smtp.gmail.com>> src\main\resources\application-railway.yml
echo   port: 587>> src\main\resources\application-railway.yml
echo   username: ${MAIL_USERNAME}>> src\main\resources\application-railway.yml
echo   password: ${MAIL_PASSWORD}>> src\main\resources\application-railway.yml
echo    - application-railway.yml: CREADO ✓

echo.
echo [3/6] ✅ PREPARANDO GITHUB:
echo.

echo Inicializando repositorio Git...
cd ..
git init >nul 2>&1
if %errorlevel%==0 (
    echo    - Git repo: INICIALIZADO ✓
) else (
    echo    - Git repo: YA EXISTIA ✓
)

echo.
echo Creando .gitignore...
echo target/> .gitignore
echo .mvn/> .gitignore
echo mvnw>> .gitignore
echo mvnw.cmd>> .gitignore
echo .DS_Store>> .gitignore
echo *.log>> .gitignore
echo .env>> .gitignore
echo    - .gitignore: CREADO ✓

echo.
echo [4/6] ✅ INSTRUCCIONES PARA RAILWAY:
echo.
echo ========================================
echo 🚀 PASOS PARA DESPLEGAR EN RAILWAY
echo ========================================
echo.
echo 1. CREAR CUENTA EN RAILWAY:
echo    - Ve a: https://railway.app/
echo    - Haz clic en "Sign Up"
echo    - Conecta con GitHub
echo.
echo 2. CREAR PROYECTO:
echo    - Haz clic en "New Project"
echo    - Selecciona "Deploy from GitHub repo"
echo    - Conecta tu repositorio
echo.
echo 3. CONFIGURAR BASE DE DATOS:
echo    - En tu proyecto, haz clic en "+"
echo    - Selecciona "Database" → "PostgreSQL"
echo    - Railway creará automáticamente la DB
echo.
echo 4. CONFIGURAR VARIABLES:
echo    - Ve a tu servicio de backend
echo    - Haz clic en "Variables"
echo    - Agrega estas variables:
echo.
echo    DATABASE_URL = (Railway lo genera automáticamente)
echo    JWT_SECRET = ecovive_jwt_secret_key_2024_very_secure_256_bits
echo    AWS_ACCESS_KEY_ID = (opcional para empezar)
echo    AWS_SECRET_ACCESS_KEY = (opcional para empezar)
echo    MAIL_USERNAME = tu_email@gmail.com
echo    MAIL_PASSWORD = tu_password_de_app
echo.
echo 5. DESPLEGAR:
echo    - Railway detectará automáticamente que es Java/Maven
echo    - Compilará y desplegará automáticamente
echo    - Te dará una URL como: https://tu-app.railway.app
echo.
echo [5/6] ✅ COMANDOS PARA SUBIR A GITHUB:
echo.
echo git add .
echo git commit -m "Backend EcoVive Perú para Railway"
echo git branch -M main
echo git remote add origin https://github.com/TU_USUARIO/ecovive-backend.git
echo git push -u origin main
echo.
echo [6/6] ✅ VENTAJAS DE RAILWAY:
echo.
echo ✅ COMPLETAMENTE GRATIS para empezar
echo ✅ Base de datos PostgreSQL incluida
echo ✅ Despliegue automático desde GitHub
echo ✅ HTTPS automático
echo ✅ Variables de entorno fáciles
echo ✅ Logs en tiempo real
echo ✅ Escalado automático
echo ✅ Sin configuración de servidor
echo.
echo ========================================
echo 🎉 LISTO PARA DESPLEGAR GRATIS
echo ========================================
echo.
echo 📱 DESPUÉS DEL DESPLIEGUE:
echo   1. Tu API estará en: https://tu-app.railway.app/api
echo   2. Documentación en: https://tu-app.railway.app/api/swagger-ui.html
echo   3. Health check en: https://tu-app.railway.app/api/actuator/health
echo   4. Cambia la URL en tu app móvil a la de Railway
echo.
echo 🚀 PRÓXIMOS PASOS:
echo   1. Sube el código a GitHub
echo   2. Conecta Railway con GitHub
echo   3. Configura las variables
echo   4. ¡Disfruta tu backend gratis!
echo.
echo 💡 TIPS:
echo   - Railway te da 500 horas gratis al mes
echo   - Base de datos PostgreSQL incluida
echo   - Puedes actualizar con solo hacer push a GitHub
echo   - Perfecto para desarrollo y pruebas
echo.
echo 🎊 ¡TU BACKEND ESTARÁ EN LA NUBE EN 10 MINUTOS!
echo ========================================
pause
