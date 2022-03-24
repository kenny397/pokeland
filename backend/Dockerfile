FROM openjdk:17-ea-11-jdk-slim

WORKDIR /server

COPY ./build/libs/b208-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "todo-server.jar"]