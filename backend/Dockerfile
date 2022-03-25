FROM openjdk:17-ea-11-jdk-slim

WORKDIR /server

COPY ./build/libs/b208-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
