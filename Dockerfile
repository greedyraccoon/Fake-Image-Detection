# Use a lightweight Java 17 environment
FROM eclipse-temurin:17-jdk-alpine

# Set the port your app runs on
EXPOSE 8080

# Copy the built Java application into the Docker container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
