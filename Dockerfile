FROM adoptopenjdk/openjdk11:alpine
LABEL maintainer="allanborges@gmail.com"
WORKDIR /alelo-apps-dir
COPY /alelo-frota-app/target/alelo-frota-app-0.0.1-SNAPSHOT.jar alelo-frota-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","alelo-frota-app.jar"]