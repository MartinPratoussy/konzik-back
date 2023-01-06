FROM openjdk:17-jdk-slim as builder

# Installer Maven
RUN apt-get update && \
    apt-get install -y maven

# Ajouter konzik-back-dev.tar dans le dossier app
ADD ../../Chat /app/Chat

# Créer un dossier APPLI
RUN mkdir /app/APPLI

WORKDIR /app/Chat
RUN mvn compile && mvn install -DskipTests && cp /app/Chat/target/*.jar /app/APPLI/chat.jar
RUN echo "Chat ok !"

FROM maven:latest as runner

COPY --from=builder /app/APPLI/ .
CMD java -jar chat.jar

#OK
