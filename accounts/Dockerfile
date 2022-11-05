# Start with a base image containing Java runtime
FROM openjdk:17.0.1-jdk-slim as build

# Information around who maintains the image
MAINTAINER snganou.de

# Defining working directory
WORKDIR /application

# Add the application's jar to the container
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# Execute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]