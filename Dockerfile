FROM maven:3.3-jdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
RUN apt-get update \
        && apt-get install -y wget \
        && wget -O - https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz | tar xzf - -C /usr/local/bin \
        && apt-get autoremove -yqq --purge wget && rm -rf /var/lib/apt/lists/*


COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["dockerize", "-wait", "tcp://mysql_db:3306", "-timeout", "60s", "java", "-jar", "/app.jar"]
