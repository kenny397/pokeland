FROM node:latest

WORKDIR /app
COPY package* ./
RUN npm install 
COPY public ./public 
COPY src ./src
RUN npm run build
EXPOSE 5000
# 2. 빌드 된 파일을 배포용 이미지에 복사
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
