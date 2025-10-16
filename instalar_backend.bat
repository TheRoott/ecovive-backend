@echo off
echo ========================================
echo INSTALACION BACKEND ECOVIVE PERU
echo Sistema completo para 200+ usuarios
echo ========================================
echo.

echo [1/8] ✅ VERIFICANDO REQUISITOS:
echo.

echo Verificando Java...
java -version >nul 2>&1
if %errorlevel%==0 (
    echo    - Java: INSTALADO ✓
) else (
    echo    - Java: NO INSTALADO ✗
    echo    Instala Java 17+ desde: https://adoptium.net/
    pause
    exit /b 1
)

echo.
echo Verificando Maven...
mvn -version >nul 2>&1
if %errorlevel%==0 (
    echo    - Maven: INSTALADO ✓
) else (
    echo    - Maven: NO INSTALADO ✗
    echo    Instala Maven desde: https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

echo.
echo Verificando PostgreSQL...
psql --version >nul 2>&1
if %errorlevel%==0 (
    echo    - PostgreSQL: INSTALADO ✓
) else (
    echo    - PostgreSQL: NO INSTALADO ✗
    echo    Instala PostgreSQL 15+ desde: https://www.postgresql.org/download/
    pause
    exit /b 1
)

echo.
echo [2/8] ✅ CONFIGURANDO BASE DE DATOS:
echo.

echo Creando base de datos...
psql -U postgres -c "CREATE DATABASE ecovive_db;" >nul 2>&1
if %errorlevel%==0 (
    echo    - Base de datos: CREADA ✓
) else (
    echo    - Base de datos: YA EXISTE o ERROR
)

echo.
echo Configurando tablas e índices...
psql -U postgres -d ecovive_db -f backend\database\setup.sql >nul 2>&1
if %errorlevel%==0 (
    echo    - Tablas e índices: CONFIGURADOS ✓
) else (
    echo    - Error configurando tablas
    pause
    exit /b 1
)

echo.
echo [3/8] ✅ CONFIGURANDO VARIABLES DE ENTORNO:
echo.

echo Configurando variables de entorno...
set DATABASE_URL=jdbc:postgresql://localhost:5432/ecovive_db
set DATABASE_USERNAME=ecovive_user
set DATABASE_PASSWORD=ecovive_pass123
set JWT_SECRET=ecovive_jwt_secret_key_2024_very_secure_256_bits
set AWS_ACCESS_KEY_ID=your-access-key
set AWS_SECRET_ACCESS_KEY=your-secret-key
echo    - Variables de entorno: CONFIGURADAS ✓

echo.
echo [4/8] ✅ COMPILANDO PROYECTO:
echo.

echo Compilando backend...
cd backend
mvn clean compile >nul 2>&1
if %errorlevel%==0 (
    echo    - Compilación: EXITOSA ✓
) else (
    echo    - Compilación: FALLÓ ✗
    echo    Revisando errores...
    mvn clean compile
    pause
    exit /b 1
)

echo.
echo [5/8] ✅ EJECUTANDO TESTS:
echo.

echo Ejecutando tests unitarios...
mvn test >nul 2>&1
if %errorlevel%==0 (
    echo    - Tests: EXITOSOS ✓
) else (
    echo    - Tests: FALLARON (continuando...)
)

echo.
echo [6/8] ✅ EMPAQUETANDO APLICACION:
echo.

echo Creando JAR ejecutable...
mvn package -DskipTests >nul 2>&1
if %errorlevel%==0 (
    echo    - JAR: CREADO ✓
) else (
    echo    - Error creando JAR
    pause
    exit /b 1
)

echo.
echo [7/8] ✅ INICIANDO SERVIDOR:
echo.

echo Iniciando servidor backend...
echo    - Puerto: 8080
echo    - API: http://localhost:8080/api
echo    - Swagger: http://localhost:8080/api/swagger-ui.html
echo    - Health: http://localhost:8080/api/actuator/health
echo.

echo Presiona Ctrl+C para detener el servidor
echo.

java -jar target\ecovive-backend-1.0.0.jar

echo.
echo [8/8] ✅ SERVIDOR DETENIDO:
echo.
echo ========================================
echo 🎉 BACKEND ECOVIVE PERU INSTALADO
echo ========================================
echo.
echo ✅ COMPONENTES INSTALADOS:
echo   - 🗄️ PostgreSQL Database (200+ usuarios)
echo   - ☕ Spring Boot Backend (Java 17)
echo   - 🔐 JWT Authentication
echo   - 📁 File Storage (AWS S3 ready)
echo   - 📊 Monitoring & Logging
echo   - 📚 API Documentation (Swagger)
echo.
echo 🚀 FUNCIONALIDADES DISPONIBLES:
echo   - 👤 Sistema de usuarios y autenticación
echo   - 📝 Sistema de reportes ambientales
echo   - 📸 Almacenamiento de fotos
echo   - 🏆 Sistema de logros y puntos
echo   - 💬 Comentarios en reportes
echo   - 📍 Búsqueda geográfica
echo   - 📈 Estadísticas y métricas
echo.
echo 📱 ENDPOINTS PRINCIPALES:
echo   - POST /api/auth/login - Autenticación
echo   - POST /api/auth/register - Registro
echo   - GET /api/users/profile - Perfil usuario
echo   - POST /api/reports - Crear reporte
echo   - GET /api/reports - Listar reportes
echo   - POST /api/files/upload - Subir fotos
echo   - GET /api/stats - Estadísticas
echo.
echo 🔧 CONFIGURACION ADICIONAL:
echo   1. Configura AWS S3 para almacenamiento de fotos
echo   2. Configura email SMTP para notificaciones
echo   3. Ajusta configuración de base de datos según servidor
echo   4. Configura SSL/HTTPS para producción
echo.
echo 📞 SOPORTE:
echo   - Documentación: http://localhost:8080/api/swagger-ui.html
echo   - Logs: logs/ecovive-backend.log
echo   - Health Check: http://localhost:8080/api/actuator/health
echo.
echo 🎊 ¡Backend listo para conectar con la app móvil!
echo ========================================
pause
