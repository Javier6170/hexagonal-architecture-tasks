# Imagen base de Java 17
FROM openjdk:17-jdk-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el JAR generado
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Comando para ejecutar el servicio
ENTRYPOINT ["java", "-jar", "app.jar"]
