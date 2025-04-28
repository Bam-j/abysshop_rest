# Build 단계
FROM node:20 as build
WORKDIR /app

ARG REACT_APP_API_BASE_URL
ENV REACT_APP_API_BASE_URL=${REACT_APP_API_BASE_URL}

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

CMD ["sh", "-c", "serve -s build -l ${PORT}"]
