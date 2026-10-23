@echo off
cd /d "%~dp0"
if not exist bin mkdir bin
javac -encoding UTF-8 -d bin -sourcepath src src\UI\Main.java
if errorlevel 1 (
    pause
    exit /b 1
)
java -cp bin UI.Main
pause
