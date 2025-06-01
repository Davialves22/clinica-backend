# # FROM openjdk:17-slim
# # WORKDIR /app
# # COPY . /app
# # RUN mvn clean package -DskipTests
# # CMD ["java", "-jar", "target/demo-security-0.0.1-SNAPSHOT.jar"]

# # Build stage: compila o projeto com Maven
# FROM maven:3.8.5-openjdk-17 AS build
# WORKDIR /app
# COPY . .
# RUN mvn clean package -DskipTests

# # Run stage: roda o JAR gerado
# FROM openjdk:17-slim
# WORKDIR /app
# COPY --from=build /app/target/demo-security-0.0.1-SNAPSHOT.jar app.jar
# EXPOSE 8080
# CMD ["java", "-jar", "app.jar"]

# FROM openjdk:17-slim
# WORKDIR /app
# COPY ./pom.xml ./
# COPY ./src ./src

# RUN apt-get update && apt-get install -y maven

# RUN mvn clean package -DskipTests

# CMD ["java", "-jar", "target/demo-security-0.0.1-SNAPSHOT.jar"]

# # Stage 1: Build com Maven
# FROM maven:3.8.5-openjdk-17 AS build
# WORKDIR /app
# COPY pom.xml .
# COPY src ./src
# RUN mvn clean package -DskipTests

# # Stage 2: Imagem final só com OpenJDK e o .jar
# FROM openjdk:17-slim
# WORKDIR /app
# COPY --from=build /app/target/demo-security-0.0.1-SNAPSHOT.jar app.jar
# EXPOSE 8080
# CMD ["java", "-jar", "app.jar"]

FROM maven:3.9.4-eclipse-temurin-17

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/spring-security-0.0.1-SNAPSHOT.jar"]