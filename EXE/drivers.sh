#!/bin/bash

echo "Seleccione el numero del driver que desea ejecutar:"
echo "1 -> Demo"
echo "2 -> DriverDomainController"
echo "3 -> DriverGameController"

read option
if [ "$option" == "1" ]; then
	java -cp . drivers.Demo
elif [ "$option" == "2" ]; then
	java -cp . drivers.DriverDomainController
elif [ "$option" == "3" ]; then
	java -cp . drivers.DriverGameController
else
	echo "Opcion invalida"
fi



