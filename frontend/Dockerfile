# Node.js 환경을 가진 이미지 사용
FROM node:18

# 작업 디렉토리 설정
WORKDIR /app

# package.json과 package-lock.json을 복사
COPY package*.json ./

# 의존성 설치
RUN npm install

# 소스 코드 복사
COPY . .

# 빌드 실행
RUN npm run build

# Nginx를 이용해 정적 파일 제공
FROM nginx:alpine
COPY --from=0 /app/build /usr/share/nginx/html

# Nginx 포트 설정
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
