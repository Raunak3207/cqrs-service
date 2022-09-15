FROM openjdk:17
EXPOSE 8080
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} cqrs_event_sourcing.jar
ENTRYPOINT ["java","-jar","/cqrs_event_sourcing.jar"]