FROM openjdk:17-alpine
WORKDIR /app
COPY target/*.jar enrollment.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "enrollment.jar"]
