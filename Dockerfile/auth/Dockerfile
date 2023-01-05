FROM openjdk:17-jdk-slim as builder

# Installer Maven
RUN apt-get update && \
    apt-get install -y maven


# Ajouter konzik-back-dev.tar dans le dossier app
ADD ../../Auth /app/Auth
ADD ../../Common /app/Common

# Créer un dossier APPLI
RUN mkdir /app/APPLI

#Problem compilation manque BDD
WORKDIR /app/Common
RUN mvn compile && mvn install
WORKDIR /app/Auth
RUN mvn compile && mvn install -DskipTests && cp /app/Auth/target/*.jar /app/APPLI/auth.jar
RUN echo "Auth ok !"

FROM debian:latest  as runner

# Install OpenJDK 17
RUN apt-get update && \
    apt-get install -y apt-utils curl openjdk-17-jdk openjdk-17-jre && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY --from=builder /app/APPLI/ .
CMD java -jar auth.jar

