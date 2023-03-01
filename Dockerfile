FROM openjdk:11
WORKDIR /usr/app
COPY target/helloworld-0.0.1-SNAPSHOT.jar /usr/app
CMD ["java", "-jar", "/usr/app/helloworld-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080

