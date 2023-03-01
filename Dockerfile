FROM openjdk:11
WORKDIR /usr/app
COPY target/helloworld-0.0.2.jar /usr/app
CMD ["java", "-jar", "/usr/app/helloworld-0.0.2.jar"]
EXPOSE 8080

