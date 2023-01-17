FROM openjdk:11-jre-slim
ARG JAR_FILE=target/back-sb-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
