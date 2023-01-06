FROM openjdk:17-jdk-slim as builder

# Installer Maven
RUN apt-get update && \
    apt-get install -y maven

# Ajouter konzik-back-dev.tar dans le dossier app
ADD ../../Concert /app/Concert
ADD ../../Common /app/Common

# Créer un dossier APPLI
RUN mkdir /app/APPLI

#Problem compilation manque BDD
WORKDIR /app/Common
RUN mvn compile && mvn install
WORKDIR /app/Concert
RUN mvn compile && mvn install -DskipTests && cp /app/Concert/target/*.jar /app/APPLI/concert.jar
RUN echo "Concert ok !"

FROM maven:latest as runner

COPY --from=builder /app/APPLI/ .
CMD java -jar concert.jar

