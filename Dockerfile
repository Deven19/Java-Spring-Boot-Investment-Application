FROM openjdk:21-jdk

COPY target/Investment-Application-0.0.1-SNAPSHOT.jar .

EXPOSE 9091

ENTRYPOINT ["java", "-jar", "Investment-Application-0.0.1-SNAPSHOT.jar"]