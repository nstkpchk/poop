# Etap 1: Budowanie aplikacji za pomocą Mavena
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
# Kopiujemy pliki projektu (pom.xml i kod źródłowy)
COPY . .
# Budujemy paczkę JAR, pomijając testy dla szybkości
RUN mvn clean package -DskipTests

# Etap 2: Uruchamianie aplikacji za pomocą JRE (zgodnie z Zadaniem 2/3 - Temurin)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Kopiujemy tylko zbudowany plik JAR z poprzedniego etapu
COPY --from=build /app/target/*.jar app.jar

# Gateway zazwyczaj działa na porcie 8080
EXPOSE 8080

# Komenda startowa
ENTRYPOINT ["java", "-jar", "app.jar"]