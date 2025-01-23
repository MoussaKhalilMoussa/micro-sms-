FROM openjdk:17-alpine
WORKDIR /app
COPY target/*.jar person.jar
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "person.jar"]
