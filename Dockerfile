FROM openjdk:17-jdk-alpine
COPY ctf-treasure-map-service-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
CMD ["java", "-jar", "ctf-treasure-map-service-0.0.1-SNAPSHOT.jar"]