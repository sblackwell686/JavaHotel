# Use a Java runtime as base imageFROM openjdk:17

# Copy the JAR file generated by MavenCOPY target/D387_sample_code-0.0.2-SNAPSHOT.jar app.jar

# Command to run the applicationCMD ["java", "-jar", "app.jar"]
FROM openjdk:17
ADD target/*.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
EXPOSE 8080