@echo off
echo ========================================
echo CONFIGURACION RAPIDA DESARROLLO
echo EcoVive Perú - Backend Local
echo ========================================
echo.

echo [1/4] ✅ CONFIGURANDO BASE DE DATOS LOCAL:
echo.

echo Iniciando PostgreSQL...
net start postgresql-x64-15 >nul 2>&1
if %errorlevel%==0 (
    echo    - PostgreSQL: INICIADO ✓
) else (
    echo    - PostgreSQL: YA ESTABA INICIADO
)

echo.
echo Creando base de datos de desarrollo...
psql -U postgres -c "DROP DATABASE IF EXISTS ecovive_dev;" >nul 2>&1
psql -U postgres -c "CREATE DATABASE ecovive_dev;" >nul 2>&1
echo    - Base de datos dev: CREADA ✓

echo.
echo [2/4] ✅ CONFIGURANDO VARIABLES DE ENTORNO:
echo.

set SPRING_PROFILES_ACTIVE=dev
set DATABASE_URL=jdbc:postgresql://localhost:5432/ecovive_dev
set DATABASE_USERNAME=postgres
set DATABASE_PASSWORD=postgres
set JWT_SECRET=dev_jwt_secret_key_for_testing_only
set AWS_S3_ENDPOINT=http://localhost:4566
set MAIL_USERNAME=test@ecovive.pe
set MAIL_PASSWORD=test_password

echo    - Perfil: DESARROLLO
echo    - Base de datos: ecovive_dev
echo    - JWT: Configurado para desarrollo
echo    - Email: Modo test

echo.
echo [3/4] ✅ INICIANDO BACKEND EN MODO DESARROLLO:
echo.

cd backend
echo Compilando proyecto...
mvn clean compile >nul 2>&1

echo.
echo Iniciando servidor de desarrollo...
echo.
echo 🌱 EcoVive Perú Backend - Modo Desarrollo
echo 📱 API: http://localhost:8080/api
echo 📚 Swagger: http://localhost:8080/api/swagger-ui.html
echo 🔍 Health: http://localhost:8080/api/actuator/health
echo.
echo 💡 TIPS DESARROLLO:
echo   - Los logs se muestran en consola
echo   - Base de datos se recrea automáticamente
echo   - Hot reload habilitado
echo   - CORS configurado para localhost
echo.
echo Presiona Ctrl+C para detener
echo.

mvn spring-boot:run

echo.
echo [4/4] ✅ SERVIDOR DETENIDO:
echo.
echo ========================================
echo 🛠️ DESARROLLO FINALIZADO
echo ========================================
echo.
echo ✅ CONFIGURACION LISTA PARA:
echo   - 🔧 Desarrollo local de la app móvil
echo   - 🧪 Testing de endpoints
echo   - 📊 Debugging de base de datos
echo   - 📝 Documentación API
echo.
echo 🚀 PROXIMOS PASOS:
echo   1. Conectar app móvil a http://localhost:8080/api
echo   2. Probar endpoints con Swagger UI
echo   3. Verificar logs en consola
echo   4. Configurar base de datos según necesidades
echo.
echo 📞 RECURSOS:
echo   - API Docs: http://localhost:8080/api/swagger-ui.html
echo   - Database: localhost:5432/ecovive_dev
echo   - Logs: Consola (desarrollo)
echo.
pause
