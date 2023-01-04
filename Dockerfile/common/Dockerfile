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
source ~/.bashrc

# Ajouter konzik-back-dev.tar dans le dossier app
ADD ../../ /app/

# Créer un dossier APPLI
RUN mkdir /app/APPLI

WORKDIR /app/Common
RUN mvn compile && mvn install && cp /app/Common/target/*.jar /app/APPLI/common.jar
RUN echo "Common ok !"

FROM debian:latest as runner

# Install OpenJDK 17
RUN apt-get update && \
    apt-get install -y apt-utils curl openjdk-17-jdk openjdk-17-jre && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY --from=builder /app/APPLI/ .
CMD java -jar common.jar

#OK
