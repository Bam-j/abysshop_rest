# 1. JAR 파일을 빌드하는 단계
FROM gradle:jdk17 AS builder
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew build --no-daemon

# 2. 빌드된 JAR을 실행하는 단계
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
EXPOSE 8080