FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} movieservice.jar

ENTRYPOINT ["java", "-jar", "/movieservice.jar"]

EXPOSE 8761