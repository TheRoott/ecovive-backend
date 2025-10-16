@echo off
echo ========================================
echo SUBIENDO A NUEVO REPOSITORIO GITHUB
echo TheRoott/ecovive-backend
echo ========================================
echo.

echo [1/7] ✅ VERIFICANDO CONFIGURACION:
echo.

echo Configurando usuario Git...
git config user.name "TheRoott"
git config user.email "theroott@example.com"

echo.
echo [2/7] ✅ CAMBIANDO REPOSITORIO REMOTO:
echo.

echo Eliminando repositorio anterior...
git remote remove origin

echo Agregando nuevo repositorio...
git remote add origin https://github.com/TheRoott/ecovive-backend.git

echo.
echo [3/7] ✅ VERIFICANDO ESTADO:
echo.

git status

echo.
echo [4/7] ✅ AGREGANDO ARCHIVOS:
echo.

git add .

echo.
echo [5/7] ✅ HACIENDO COMMIT:
echo.

git commit -m "Backend EcoVive Perú completo - Sistema para 200+ usuarios con PostgreSQL, JWT, AWS S3 y API REST"

echo.
echo [6/7] ✅ SUBIENDO A GITHUB:
echo.

git branch -M main
git push -u origin main

echo.
echo [7/7] ✅ VERIFICACION:
echo.

git remote -v

echo.
echo ========================================
echo 🎉 CODIGO SUBIDO AL NUEVO REPOSITORIO
echo ========================================
echo.
echo ✅ TU REPOSITORIO ESTÁ EN:
echo    https://github.com/TheRoott/ecovive-backend.git
echo.
echo 🚀 AHORA PUEDES DESPLEGAR EN RAILWAY:
echo    1. Ve a: https://railway.app/
echo    2. Sign Up con GitHub
echo    3. New Project → Deploy from GitHub repo
echo    4. Selecciona: TheRoott/ecovive-backend
echo    5. ¡Desplegar!
echo.
echo 📱 DESPUÉS DEL DESPLIEGUE:
echo    - Tu API estará en: https://tu-app.railway.app/api
echo    - Documentación: https://tu-app.railway.app/api/swagger-ui.html
echo    - Health Check: https://tu-app.railway.app/api/actuator/health
echo.
echo 🎊 ¡LISTO PARA DESPLEGAR EN LA NUBE!
echo ========================================
pause
