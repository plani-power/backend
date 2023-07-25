FROM eclipse-temurin:18
RUN mkdir /opt/app
COPY ./target/back-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "-Dspring.profiles.active=dev", "/opt/app/back-0.0.1-SNAPSHOT.jar"]
