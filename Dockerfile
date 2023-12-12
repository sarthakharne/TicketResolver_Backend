# Fetching latest version of Java
FROM openjdk:11

# Setting up work directory
WORKDIR .

# Copy the jar file into our app
COPY ./target/ticketresolver-0.0.1-SNAPSHOT.jar .

# Exposing port 8080
EXPOSE 8080

# Starting the application 
CMD ["java", "-jar", "ticketresolver-0.0.1-SNAPSHOT.jar"]