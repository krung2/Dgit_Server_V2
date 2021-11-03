FROM openjdk:11-jre-slim

WORKDIR /usr/src/dgitv2

COPY *.jar app.jar

ENV TZ=Asia/Seoul

ENTRYPOINT ["java", "-jar", "./app.jar"]
