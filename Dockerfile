FROM openjdk

WORKDIR /app

COPY target/stoom-0.0.1-SNAPSHOT.jar /app/stoom.jar

ENTRYPOINT ["java", "-jar", "stoom.jar"]