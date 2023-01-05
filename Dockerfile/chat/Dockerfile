FROM debian:latest as builder

# Install OpenJDK 17
RUN apt-get update && \
    apt-get install -y apt-utils curl openjdk-17-jdk openjdk-17-jre && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

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

FROM debian:latest  as runner

# Install OpenJDK 17
RUN apt-get update && \
    apt-get install -y apt-utils curl openjdk-17-jdk openjdk-17-jre && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY --from=builder /app/APPLI/ .
CMD java -jar chat.jar

#OK
