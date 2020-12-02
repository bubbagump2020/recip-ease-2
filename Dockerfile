FROM openjdk:8-jdk-alpine
MAINTAINER experto.com
VOLUME /tmp
ADD target/Recip-Ease2.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 9011