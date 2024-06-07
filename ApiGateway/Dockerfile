# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# The application's jar file
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
COPY ${JAR_FILE} apigateway.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/apigateway.jar"]

# Make port 8080 available to the world outside this container
EXPOSE 9090