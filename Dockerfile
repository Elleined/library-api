FROM openjdk:17-alpine
MAINTAINER Elleined

# Docker MySQL Credentials
ENV MYSQL_HOST=la_mysql_server
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_PORT=3306
ENV MYSQL_DATABASE=la_db

ADD ./target/*.jar library-api.jar
EXPOSE 8090
CMD ["java", "-jar", "library-api.jar"]