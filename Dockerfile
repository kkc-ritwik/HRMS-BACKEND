# Use a base image that includes Java
FROM openjdk:17-jdk-slim as build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper files and pom.xml
COPY mvnw pom.xml ./

# Copy the Maven wrapper jar
COPY .mvn .mvn

# Make the Maven wrapper executable
RUN chmod +x mvnw

# Download Maven dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# Copy the jar file built in the previous stage
COPY --from=build /app/target/*.jar /app/app.jar

# Expose the port your Spring Boot app will run on
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "/app/app.jar"]