# Fetching latest version of Java
FROM openjdk:11

# Setting up work directory
WORKDIR .

# Copy the jar file into our app
COPY ./target/ticketresolver-0.0.1-SNAPSHOT.jar .

# Exposing port 8081
EXPOSE 8081

# Starting the application 
CMD ["java", "-jar", "ticketresolver-0.0.1-SNAPSHOT.jar"]