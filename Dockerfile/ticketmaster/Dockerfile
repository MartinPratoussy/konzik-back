FROM openjdk:17-jdk-slim as builder

# Installer Maven
RUN apt-get update && \
    apt-get install -y maven

# Ajouter konzik-back-dev.tar dans le dossier app
ADD ../../ /app/

# Créer un dossier APPLI
RUN mkdir /app/APPLI

WORKDIR /app/Ticketmaster
RUN mvn compile && mvn install && cp /app/Ticketmaster/target/*.jar /app/APPLI/ticketmaster.jar
RUN echo "Ticketmaster ok !"

FROM openjdk:17-jdk-slim as runner

COPY --from=builder /app/APPLI/ .
CMD java -jar ticketmaster.jar

#OK