# Etap 1: Budowanie (Maven + Temurin)
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
# Kopiujemy pliki projektu
COPY . .
# Budujemy plik .jar (pomijamy testy, żeby było szybciej)
RUN mvn clean package -DskipTests

# Etap 2: Uruchamianie (JRE Temurin)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Kopiujemy zbudowany plik jar z poprzedniego etapu
COPY --from=build /app/target/*.jar app.jar

# Deklarujemy port (Zadanie wymaga odpowiedniego portu)
EXPOSE 8081

# Uruchamiamy aplikację z użyciem zmiennych środowiskowych
ENTRYPOINT ["java", "-jar", "app.jar"]