# Stage 1: Build
FROM maven:3.8.4-openjdk-17 AS build
COPY src/main/java/com/cgrmanjeera/hotel .
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM openjdk:17-jdk-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]