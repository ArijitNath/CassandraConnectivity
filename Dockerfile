#Base Image
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="arijitnath92@gmail.com"

#Port to expose
EXPOSE 8070

#Env varibale for Working Directory
ENV CC_APP_HOME /usr/src/app

#Locate and Create JAR path
ARG CASSANDRA_CONNECTIVITY_JAR=target/cassandraconnectivity*.jar

#Copy jar to container app directory
COPY $CASSANDRA_CONNECTIVITY_JAR $CC_APP_HOME/cassandraconnectivity.jar

#Set Working Directory
WORKDIR $CC_APP_HOME

#Start Command
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","cassandraconnectivity.jar"]