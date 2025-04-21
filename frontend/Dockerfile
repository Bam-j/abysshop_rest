# Build 단계
FROM node:20 as build
WORKDIR /app
COPY frontend/package*.json ./
RUN npm install
COPY frontend/ ./
RUN npm run build

# Serve 단계
FROM node:20-alpine
RUN npm install -g serve
WORKDIR /app
COPY --from=build /app/build ./build

EXPOSE 80
CMD ["serve", "-s", "build", "-l", "80"]
