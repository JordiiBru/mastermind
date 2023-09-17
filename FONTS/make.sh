#!/bin/bash

# Compilar archivos .java en archivos .class
javac -d . -cp .:lib/* domain/*.java domain/exceptions/*.java utils/*.java test/*.java drivers/*.java drivers/stubs/*.java persistence/*.java presentation/*.java

# Crear la estructura de carpetas en EXE
mkdir -p ../EXE/domain
mkdir -p ../EXE/domain/exceptions
mkdir -p ../EXE/utils
mkdir -p ../EXE/test
mkdir -p ../EXE/drivers
mkdir -p ../EXE/drivers/stubs
mkdir -p ../EXE/persistence
mkdir -p ../EXE/presentation

# Mover archivos .class a la carpeta de destino de EXE
mv domain/*.class ../EXE/domain
mv domain/exceptions/*.class ../EXE/domain/exceptions
mv utils/*.class ../EXE/utils
mv test/*.class ../EXE/test
mv drivers/*.class ../EXE/drivers
mv drivers/stubs/*.class ../EXE/drivers/stubs
mv persistence/*.class ../EXE/persistence
mv presentation/*.class ../EXE/presentation
