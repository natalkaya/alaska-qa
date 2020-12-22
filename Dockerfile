FROM maven:3.5.3-jdk-8-alpine
MAINTAINER Natalia
RUN mkdir app
COPY ./. /app
WORKDIR /app
RUN mvn compile
VOLUME "/app/target"
CMD ["mvn", "test"]