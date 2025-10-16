FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar archivos del backend
COPY backend/ ./backend/

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Compilar el proyecto
WORKDIR /app/backend
RUN mvn clean package -DskipTests

# Exponer puerto
EXPOSE 8080

# Comando de inicio
CMD ["java", "-jar", "target/ecovive-backend-1.0.0.jar"]
