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
ADD ../../ /app/

# Créer un dossier APPLI
RUN mkdir /app/APPLI

WORKDIR /app/Common
RUN mvn compile 
#&& mvn install
RUN echo "11111111111111111111111111111111111111111111111111111"
#RUN cp /app/Common/target/*.jar /app/APPLI/common.jar
RUN echo "222222222222222222222222222222222222222222222222222"


FROM debian:latest as runner

# Install OpenJDK 17
RUN apt-get update && \
    apt-get install -y apt-utils curl openjdk-17-jdk openjdk-17-jre && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*
RUN echo "33333333333333333333333333333333333333333333333333333"
COPY --from=builder /app/APPLI/ .
CMD java -jar common.jar

#OK
