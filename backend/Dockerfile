# 1. JAR 파일을 빌드하는 단계
FROM gradle:jdk17 AS builder
WORKDIR /app
COPY . .
RUN chmod +x gradlew

# clean + build (테스트 스킵, 데몬 비활성화)
RUN ./gradlew clean build --no-daemon -x test

# 2. 빌드된 JAR을 실행하는 단계
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:8080}"]
