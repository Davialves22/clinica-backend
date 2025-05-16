FROM openjdk:17-slim
WORKDIR /app
COPY . /app
RUN ./mvnw clean package -DskipTests
CMD ["java", "-jar", "target/seu-arquivo.jar"]
