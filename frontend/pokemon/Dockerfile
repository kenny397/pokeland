FROM node:latest

WORKDIR /app
COPY package* ./
RUN npm install 
COPY public ./public 
COPY src ./src
RUN npm run build

# 2. 빌드 된 파일을 배포용 이미지에 복사

EXPOSE 5000
