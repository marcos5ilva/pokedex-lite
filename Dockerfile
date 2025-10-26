# Use a small JDK image
# Use official OpenJDK image
FROM eclipse-temurin:17-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR into the image
COPY target/pokedex-lite-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on (8080 is EB default)
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java","-jar","app.jar"]

