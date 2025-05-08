FROM amazoncorretto:17.0.3-alpine
WORKDIR /app
COPY target/newMock-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]