FROM eclipse-temurin:17-jre
WORKDIR /app
COPY app.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-XX:+UseContainerSupport","-XX:MaxRAMPercentage=75","-jar","/app/app.jar"]

