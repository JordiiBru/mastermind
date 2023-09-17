#!/bin/bash

echo "Limpiando el directorio EXE..."
rm domain/*.class
rm domain/exceptions/*.class
rm utils/*.class
rm drivers/*.class
rm drivers/stubs/*.class
rm test/*.class
rm persistence/*.class
rm presentation/*.class
echo "Hecho!"
echo "Para poder volver a ejecutar el proyecto, dirígete a la carpeta FONTS y ejecuta make.sh"
