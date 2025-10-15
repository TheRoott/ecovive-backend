@echo off
echo ========================================
echo SUBIENDO CODIGO A GITHUB
echo ========================================
echo.

echo [1/6] ✅ CONFIGURANDO GIT:
echo.

git config user.name "Fernando"
git config user.email "therrot@example.com"

echo [2/6] ✅ INICIALIZANDO REPOSITORIO:
echo.

git init

echo [3/6] ✅ CONECTANDO CON GITHUB:
echo.

git remote add origin https://github.com/therrot/ecovive-backend.git

echo [4/6] ✅ AGREGANDO ARCHIVOS:
echo.

git add .

echo [5/6] ✅ HACIENDO COMMIT:
echo.

git commit -m "Backend EcoVive Perú completo - Sistema para 200+ usuarios con PostgreSQL, JWT, AWS S3"

echo [6/6] ✅ SUBIENDO A GITHUB:
echo.

git branch -M main
git push -u origin main

echo.
echo ========================================
echo 🎉 CODIGO SUBIDO A GITHUB
echo ========================================
echo.
echo ✅ TU REPOSITORIO ESTÁ EN:
echo    https://github.com/therrot/ecovive-backend.git
echo.
echo 🚀 AHORA PUEDES DESPLEGAR EN RAILWAY:
echo    1. Ve a: https://railway.app/
echo    2. Sign Up con GitHub
echo    3. New Project → Deploy from GitHub repo
echo    4. Selecciona: therrot/ecovive-backend
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
