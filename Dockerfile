FROM amazoncorretto:11-alpine
LABEL maintainer="Chandradip S <chandradipshivankar@gmail.com"
# RUN mvn install
WORKDIR /home/app
COPY target/springBoot-mongodb-docker.jar /home/app
EXPOSE 8080

ENTRYPOINT java -jar springBoot-mongodb-docker.jar