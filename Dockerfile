FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-alpine-3.21
MAINTAINER tsadimas
WORKDIR /app
RUN apk update && apk add curl
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
COPY --from=builder /build/target/dsProject2024-0.0.1-SNAPSHOT.jar ./application.jar
RUN chown appuser ./application.jar
USER appuser
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]
