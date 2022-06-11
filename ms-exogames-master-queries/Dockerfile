FROM gradle:4.7.0-jdk8-alpine as builder
WORKDIR /usr/local/src
COPY --chown=gradle:gradle . /usr/local/src
RUN gradle build

FROM bellsoft/liberica-openjdk-alpine:17

EXPOSE 8090

RUN mkdir /ms-master-queries

COPY --from=build /home/gradle/src/build/libs/*.jar /ms-master-queries/ms-master-queries-application.jar

ENTRYPOINT ["java","-jar","/ms-master-queries/ms-master-queries-application.jar"]