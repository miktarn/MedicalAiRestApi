FROM openjdk:17-jdk-alpine
COPY target/PredictAiSpringRestAPI-0.0.1-SNAPSHOT.jar PredictAiSpringRestAPI.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/PredictAiSpringRestAPI.jar"]
