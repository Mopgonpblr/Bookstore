FROM openjdk:22-jdk-slim

WORKDIR /app

COPY target/bookstore-1.0.jar /app/bookstore-1.0.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/bookstore-1.0.jar"]