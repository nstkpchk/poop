# Etap 1: Budowanie
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etap 2: Uruchamianie
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Deklarujemy port (dla elementów użyjemy 8082)
EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]