# 1. JAR 파일을 빌드하는 단계
FROM gradle:jdk17 AS builder

# 작업 디렉토리 설정
WORKDIR /app

# 프로젝트의 모든 파일을 컨테이너로 복사
COPY . .

# gradlew 실행 권한 부여
RUN chmod +x gradlew

# Gradle을 사용하여 프로젝트 빌드 (JAR 생성)
RUN ./gradlew build --no-daemon

# 2. 빌드된 JAR을 실행하는 단계
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일을 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 컨테이너 실행 시 실행할 명령어 지정
CMD ["java", "-jar", "app.jar"]

# 컨테이너가 사용하는 포트
EXPOSE 80