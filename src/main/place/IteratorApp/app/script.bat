@echo off

set APP_NAME=Iterator
set MAIN_JAR=iterator.jar
set MAIN_CLASS=edu.io.Main

jpackage --input . --main-jar iterator.jar --name IteratorApp --main-class com.example.Main --type app-image --app-version 1.0.0

echo.