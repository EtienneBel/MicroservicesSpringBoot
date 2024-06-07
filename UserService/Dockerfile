FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} userservice.jar

ENTRYPOINT ["java", "-jar", "/userservice.jar"]

EXPOSE 8081