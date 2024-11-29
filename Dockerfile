FROM openjdk:21-ae AS build
LABEL authors="Pytaichuk Mykhailo"

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
