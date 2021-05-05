FROM gradle:jdk11 as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN gradle build

FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.10_9_openj9-0.24.0-alpine
LABEL maintainer="PK Team"
LABEL version="0.0.1-SNAPSHOT"

ARG JAR_FILE=/home/gradle/source/build/libs/*.jar

WORKDIR /app
RUN mkdir /opt/app
COPY --from=gradleimage ${JAR_FILE} /opt/app/pk-billing.jar

ENTRYPOINT ["java", "-jar", "/opt/app/pk-billing.jar"]