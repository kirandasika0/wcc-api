FROM openjdk:8
ADD ./target/wcc-api.jar wcc-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "wcc-api.jar"]