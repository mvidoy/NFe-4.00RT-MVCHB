@echo off
echo Instalando a Calculadora no WSL...
echo.

wsl --unregister calculadora >nul 2>&1
wsl --install --no-distribution >nul 2>&1
wsl --import calculadora ..\calculadora ..\calculadora.tar.gz
wsl --set-default calculadora >nul 2>&1

echo.
echo Instalacao concluida!
echo.
echo Pressione qualquer tecla para continuar...
pause