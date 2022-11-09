FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/modbus-service-1.0.2-SNAPSHOT.jar
COPY ${JAR_FILE} modbus-service.jar
ENTRYPOINT ["java","-jar","/modbus-service.jar"]