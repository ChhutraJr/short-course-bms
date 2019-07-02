FROM openjdk:8
ADD target/bms-app-docker.war bms-app-docker.war
EXPOSE 2221
ENTRYPOINT ["java", "-jar", "bms-app-docker.war"]