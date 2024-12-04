FROM maven:3.9.2-eclipse-temurin-17-alpine AS builder
COPY ./src src/
COPY ./lombok.config lombok.config
COPY ./pom.xml pom.xml
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
COPY --from=builder target/*.jar app.jar
EXPOSE 8081
CMD ["java","-jar","/app.jar"]