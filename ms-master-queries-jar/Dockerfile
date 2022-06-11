FROM openjdk:8-jdk-alpine

COPY . .

EXPOSE 8090
EXPOSE 5432

ENTRYPOINT [ "java", "-jar", "/ms-master-queries.jar" ]