# Java 21 kullanıyoruz
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/articleworm-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8082
