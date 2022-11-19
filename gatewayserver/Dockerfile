# Start with a base image containing Java runtime
FROM openjdk:17.0.1-jdk-slim as build

# Information around who maintains the image
MAINTAINER snganou.de

# Defining working directory
WORKDIR /application

# Add the application's jar to the container
COPY target/gatewayserver-0.0.1-SNAPSHOT.jar gatewayserver-0.0.1-SNAPSHOT.jar

# Execute the application
ENTRYPOINT ["java", "-jar", "gatewayserver-0.0.1-SNAPSHOT.jar"]