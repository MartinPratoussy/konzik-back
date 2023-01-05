FROM openjdk:17-jdk-slim as builder

# Installer Maven
RUN apt-get update && \
    apt-get install -y maven

# Ajouter konzik-back-dev.tar dans le dossier app
ADD ../../ /app/

# Créer un dossier APPLI
RUN mkdir /app/APPLI

WORKDIR /app/Eureka
RUN mvn compile && mvn install -DskipTests && cp /app/Eureka/target/*.jar /app/APPLI/eureka.jar
RUN echo "Eureka ok !"

FROM openjdk:17-jdk-slim as runner

COPY --from=builder /app/APPLI/ .

CMD java -jar eureka.jar
EXPOSE 8761

#OK
