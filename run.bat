@echo off
cd /d "%~dp0"
call "%~dp0mvnw.cmd" -q package
if errorlevel 1 (
    pause
    exit /b 1
)
java -jar target\food-manager.jar
pause
