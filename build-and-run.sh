#!/bin/bash

echo "🧪 Ejecutando pruebas y compilando el proyecto con Maven..."
mvn clean verify

if [ $? -ne 0 ]; then
  echo "❌ Error en la compilación o en los tests. Deteniendo script."
  exit 1
fi

echo "🐳 Reconstruyendo imagen Docker..."
docker-compose build

echo "🚀 Levantando aplicación con Docker Compose..."
docker-compose up
