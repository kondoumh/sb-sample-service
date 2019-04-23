FROM openjdk:8-jdk-alpine

COPY ../target/sb-sample-service.jar sb-sample-service.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/sb-sample-service.jar"]