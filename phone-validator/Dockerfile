FROM openjdk:8-jdk-alpine

COPY target/phone-validator-1.0.0.jar phone-validator.jar
COPY target/samplesql.db samplesql.db
ENTRYPOINT ["java","-jar","/phone-validator.jar"]
