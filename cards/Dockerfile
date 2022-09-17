# Start with a base image containing Java runtime
FROM openjdk:11-slim as build

# Information around who maintains the image
MAINTAINER snganou.de

# Defining working directory
WORKDIR /application

# Add the application's jar to the container
COPY target/cards-0.0.1-SNAPSHOT.jar cards-0.0.1-SNAPSHOT.jar

# Execute the application
ENTRYPOINT ["java", "-jar", "cards-0.0.1-SNAPSHOT.jar"]