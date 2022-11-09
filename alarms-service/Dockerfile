FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/user-management-server-1.0.2-SNAPSHOT.jar
COPY ${JAR_FILE} user-management-server.jar
ENTRYPOINT ["java","-jar","/user-management-server.jar"]