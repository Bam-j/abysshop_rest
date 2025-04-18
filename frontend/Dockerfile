# Build 단계
FROM node:18 as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Serve 단계
FROM node:18-alpine
RUN npm install -g serve
WORKDIR /app
COPY --from=build /app/build ./build

EXPOSE 80
CMD ["serve", "-s", "build", "-l", "80"]
