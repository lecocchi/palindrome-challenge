FROM openjdk:11
MAINTAINER leandro.cocchi@hotmail.com
COPY target/palindrome-challenge-1.0.0-rc.jar palindrome-challenge-1.0.0-rc.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-Dspring.data.mongodb.uri=mongodb://mongo:27017/walmart", "-jar","/palindrome-challenge-1.0.0-rc.jar"]