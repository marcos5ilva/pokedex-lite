# eb-docker/Dockerfile
FROM amazoncorretto:17-alpine

# (good practice) run as non-root
RUN addgroup -S app && adduser -S app -G app
WORKDIR /app

# copy your fat jar built by Maven
COPY app.jar /app/app.jar

# Spring Boot default is 8080
EXPOSE 8080

USER app
# helpful JVM-in-container flags
ENTRYPOINT ["java","-XX:+UseContainerSupport","-XX:MaxRAMPercentage=75","-jar","/app/app.jar"]
