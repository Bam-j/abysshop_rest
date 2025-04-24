# 1. JAR 파일을 빌드하는 단계
FROM gradle:jdk17 AS builder
WORKDIR /app

# 권한 추가 및 의존성 캐싱을 위한 최소한 복사
COPY gradlew .
COPY build.gradle settings.gradle ./
COPY gradle gradle
RUN chmod +x gradlew

# clean + build (테스트 스킵, 데몬 비활성화)
RUN ./gradlew clean build --no-daemon -x test
COPY . .
RUN ./gradlew clean build --no-daemon -x test

# 2. 빌드된 JAR을 실행하는 단계
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:8080}"]
