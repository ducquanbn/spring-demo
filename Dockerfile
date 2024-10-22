#
# BUILD STAGE
#
FROM maven:latest AS build
WORKDIR /app
COPY src ./src
COPY pom.xml .
RUN mvn package -DskipTests

#
# PACKAGE STAGE
# docker pull bitnami/mongodb:7.0.11
# docker run -d --name mongodb-7.0.11 -p 27017:27017 -e MONGODB_ROOT_USER=root -e MONGODB_ROOT_PASSWORD=root bitnami/mongodb:7.0.11
# docker build -f Dockerfile -t spring-boot .
#
FROM openjdk:21
COPY --from=build /app/target/*.jar spring-demo.jar
EXPOSE 8080  
CMD ["java","-jar","spring-demo.jar"]
