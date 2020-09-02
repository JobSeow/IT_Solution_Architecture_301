# FROM tomcat:9.0
# RUN rm -rf ./build/libs/*
# COPY ./build/libs/maskservice-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/
# EXPOSE 8080

FROM anapsix/alpine-java
COPY ./build/libs/maskservice-0.0.1-SNAPSHOT.jar /home/maskservice-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","/home/maskservice-0.0.1-SNAPSHOT.jar"]
 