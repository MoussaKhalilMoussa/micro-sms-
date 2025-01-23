FROM openjdk:17-alpine
WORKDIR /app
COPY target/*.jar gateway.jar
EXPOSE 4000
ENTRYPOINT ["java", "-jar", "gateway.jar"]
