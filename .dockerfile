# Use a base image with the Kotlin runtime and OpenJDK
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY build/libs/rinha-backend-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your application runs on
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "app.jar"]