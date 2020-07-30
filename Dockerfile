FROM openjdk:8-jdk-alpine
LABEL maintainer="Marko Markovic"
ADD target/project-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8100
ENTRYPOINT ["java", "-jar", "/app.jar"]