# KONZIK

Service path:
 - concert service: /api/concert
	- all concerts: 	/all
	- find one: 		/find/{id}
	- display add form: 	/add
	- delete one: 		/delete/{id}
	- update one: 		/update{id}
 - auth service: /api/user
	- sign-in:		/login
	- sign-on:		/register
	- all users: 		/all
	- find one: 		/find/{username}
	- update one: 		/update/{username}
	- delete one:		/find/{username}
	- verify: 		/verification-link/{userId}
	- reset password: 	/reset-password/{userId}

How to launch:
1. Pre-requisites:
   1. download and install maven (https://maven.apache.org/download.cgi). IMPORTANT: BE SURE MAVEN IS SET FOR JAVA VERSION 17
   2. download and unzip keycloak client (https://www.keycloak.org/downloads)
   3. launch keycloak by typing the following command in the source folder: ```./bin/kc.sh start-dev``` (REMINDER: DON'T IN PRODUCTION ENVIRONMENT)
2. Installation
   1. go into _Common_ folder and type: ```mvn install```
   2. go into _Eureka_ folder and type: ```./gradlew :bootRun```
   3. go into each microservice source folder and launch them with the command ```./mvnw spring-boot:run``` in the following order:
      1. Authentication microservice (app-name: auth)
      2. Concert microservice (app-name: concert)