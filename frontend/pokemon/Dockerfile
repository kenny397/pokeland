FROM node:latest

WORKDIR /app

COPY ./package*.json /app

RUN npm install

COPY / /app

RUN npm run build

CMD ["npx", "serve", "-s", "build"]

EXPOSE 5000
