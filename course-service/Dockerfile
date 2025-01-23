FROM openjdk:17-alpine
WORKDIR /app
COPY target/*.jar course.jar
EXPOSE 2000
ENTRYPOINT ["java", "-jar", "course.jar"]
