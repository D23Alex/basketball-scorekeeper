FROM amazoncorretto:17-alpine3.19-jdk

WORKDIR /opt/app
COPY . .

RUN ./mvnw dependency:go-offline
RUN ./mvnw package

EXPOSE 8080

CMD java $JAVA_OPTS -jar target/vtb-stat-1.0.jar

