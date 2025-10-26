FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY target/pokedex-lite-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "-Dserver.port=5000", "app.jar"]