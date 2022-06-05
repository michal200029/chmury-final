FROM adoptopenjdk/openjdk16:ubi
MAINTAINER ostrowski.opalka
COPY target/invoice-generator-0.0.1-SNAPSHOT.jar invoice-generator-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/invoice-generator-0.0.1-SNAPSHOT.jar"]