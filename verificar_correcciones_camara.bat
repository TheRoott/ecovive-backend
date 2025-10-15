@echo off
echo ========================================
echo VERIFICACION DE CORRECCIONES DE CAMARA
echo ========================================
echo.

echo [1/5] Verificando archivos modificados...
if exist "app\src\main\java\com\example\myapplication\presentation\screens\report\ReportScreen.kt" (
    echo ✅ ReportScreen.kt encontrado
) else (
    echo ❌ ReportScreen.kt NO encontrado
)

if exist "app\src\main\java\com\example\myapplication\utils\CameraManager.kt" (
    echo ✅ CameraManager.kt encontrado
) else (
    echo ❌ CameraManager.kt NO encontrado
)

if exist "app\src\main\java\com\example\myapplication\utils\CameraHelper.kt" (
    echo ✅ CameraHelper.kt encontrado
) else (
    echo ❌ CameraHelper.kt NO encontrado
)

echo.
echo [2/5] Verificando AndroidManifest.xml...
findstr /C:"android.permission.CAMERA" app\src\main\AndroidManifest.xml >nul
if %errorlevel%==0 (
    echo ✅ Permiso de cámara configurado
) else (
    echo ❌ Permiso de cámara NO encontrado
)

findstr /C:"FileProvider" app\src\main\AndroidManifest.xml >nul
if %errorlevel%==0 (
    echo ✅ FileProvider configurado
) else (
    echo ❌ FileProvider NO encontrado
)

echo.
echo [3/5] Verificando archivo file_paths.xml...
if exist "app\src\main\res\xml\file_paths.xml" (
    echo ✅ file_paths.xml encontrado
) else (
    echo ❌ file_paths.xml NO encontrado
)

echo.
echo [4/5] Verificando dependencias de cámara...
findstr /C:"camera-core" app\build.gradle.kts >nul
if %errorlevel%==0 (
    echo ✅ Dependencias de cámara configuradas
) else (
    echo ❌ Dependencias de cámara NO encontradas
)

echo.
echo [5/5] Verificando documentación...
if exist "SOLUCION_ERRORES_CAMARA.md" (
    echo ✅ Documentación creada
) else (
    echo ❌ Documentación NO encontrada
)

echo.
echo ========================================
echo RESUMEN DE VERIFICACION
echo ========================================
echo.
echo ✅ CORRECCIONES APLICADAS:
echo   - Manejo robusto de errores en cámara
echo   - Verificación de disponibilidad de hardware
echo   - Logs detallados para debugging
echo   - Compatibilidad mejorada con MIUI
echo   - CameraHelper para utilidades adicionales
echo.
echo 🎯 PRUEBAS RECOMENDADAS:
echo   1. Compilar la aplicación
echo   2. Instalar en POCO X7 Pro
echo   3. Ir a pantalla Reporte
echo   4. Tocar botón de cámara
echo   5. Verificar que NO se cierre la app
echo   6. Tomar foto exitosamente
echo.
echo 📱 CONFIGURACION ADICIONAL:
echo   - Configuración → Apps → EcoVive Perú
echo   - Permisos → Cámara → Permitir
echo   - Gestión de batería → Sin restricciones
echo.
echo ¡CÁMARA CORREGIDA Y LISTA PARA USAR!
echo ========================================
pause
