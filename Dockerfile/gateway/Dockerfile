FROM openjdk:17-jdk-slim as builder

# Installer Maven
RUN apt-get update && \
    apt-get install -y maven

# Ajouter konzik-back-dev.tar dans le dossier app
ADD ../../Gateway /app/Gateway

# Créer un dossier APPLI
RUN mkdir /app/APPLI

WORKDIR /app/Gateway
RUN mvn compile && mvn install -DskipTests && cp /app/Gateway/target/*.jar /app/APPLI/gateway.jar
RUN echo "Gateway ok !"

FROM maven:latest as runner

COPY --from=builder /app/APPLI/ .

CMD java -jar gateway.jar
EXPOSE 8762
