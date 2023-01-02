FROM ubuntu:latest as builder

# Install OpenJDK 17
RUN apt-get update && \
    apt-get install -y apt-utils curl openjdk-17-jdk openjdk-17-jre && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Installer Maven
RUN apt-get update && \
    apt-get install -y maven

# Ajouter konzik-back-dev.tar dans le dossier app
ADD ../../ /app/

# Créer un dossier APPLI
RUN mkdir /app/APPLI

WORKDIR /app/Ticketmaster
RUN mvn compile && mvn install -DskipTests && cp /app/Ticketmaster/target/*.jar /app/APPLI/ticketmaster.jar
RUN echo "Ticketmaster ok !"

FROM ubuntu:latest as runner

# Install OpenJDK 17
RUN apt-get update && \
    apt-get install -y apt-utils curl openjdk-17-jdk openjdk-17-jre && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY --from=builder /app/APPLI/ .
CMD java -jar ticketmaster.jar

#OK
