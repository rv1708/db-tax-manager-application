FROM openjdk:17-jdk-slim

WORKDIR /app

CMD ["make" , "build"]

COPY target/tax-manager-1.0.0.jar tax-manager.jar

EXPOSE 8080

# Define the command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/tax-manager.jar"]
