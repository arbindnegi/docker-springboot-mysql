#1 The first part is the FROM command, which tells us what is the image
FROM openjdk:8-jdk-alpine

#2 if You want to install any softawre which is require to run our image yhen use  RUN command
#eg : RUN apt-get install nginx server

copy target/docker-springboot-mysql-0.0.1-SNAPSHOT.jar docker-springboot-mysql-0.0.1-SNAPSHOT.jar

#ENTRYPOINT & CMD is get executed when we run the container. If the ENTRYPOINT isn’t specified, Docker will use /bin/sh -c as the default.
CMD ["java","-jar","docker-springboot-mysql-0.0.1-SNAPSHOT.jar"]
##ENTRYPOINT ["java","-jar","docker-springboot-mysql-0.0.1-SNAPSHOT.jar"]

##http://192.168.99.100:8080/employees