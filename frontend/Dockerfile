# Build 단계
FROM node:20 as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Serve 단계
FROM node:20-alpine
RUN npm install -g serve
WORKDIR /app
COPY --from=build /app/build ./build

EXPOSE 8080
ENV PORT=8080

CMD ["sh", "-c", "serve -s /app/build -l $PORT"]
