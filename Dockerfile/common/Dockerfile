FROM debian:latest as builder

# Install OpenJDK 17
RUN apt-get update && \
    apt-get install -y apt-utils curl openjdk-17-jdk openjdk-17-jre && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Installer Maven
RUN wget https://downloads.apache.org/maven/maven-3/3.7.0/binaries/apache-maven-3.7.0-bin.tar.gz
RUN tar -xvf apache-maven-3.7.0-bin.tar.gz
RUN sudo mv apache-maven-3.7.0 /opt/maven
RUN echo "export PATH=/opt/maven/bin:$PATH" >> ~/.bashrc
RUN source ~/.bashrc


# Ajouter konzik-back-dev.tar dans le dossier app
ADD ../../ /app/

# Créer un dossier APPLI
RUN mkdir /app/APPLI

WORKDIR /app/Common
RUN mvn --version
