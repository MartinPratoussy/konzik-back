FROM debian:latest as builder

RUN apt-get update && \
    apt-get install -y apt-utils curl openjdk-17-jdk && \
    apt-get install -y maven && \
    apt-get clean && \
    #rm -rf /var/lib/apt/lists/*

# Ajouter konzik-back-dev.tar dans le dossier app
ADD ../../ /app/

# Créer un dossier APPLI
RUN mkdir /app/APPLI

WORKDIR /app/Eureka
RUN mvn compile && mvn install -DskipTests && cp /app/Eureka/target/*.jar /app/APPLI/eureka.jar
RUN echo "Eureka ok !"


FROM debian:latest as runner

# Install OpenJDK 17
RUN apt-get update && \
    apt-get install -y apt-utils curl openjdk-17-jdk && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY --from=builder /app/APPLI/ .

CMD java -jar eureka.jar
EXPOSE 8761
