# Usar una imagen base de OpenJDK
FROM openjdk:11-jre-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado por Maven al contenedor
COPY target/microservicio-inventario-1.0-SNAPSHOT.jar /app/microservicio-inventario.jar

# Exponer el puerto en el que correrá el servicio (8080 en este caso)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/microservicio-inventario.jar"]
