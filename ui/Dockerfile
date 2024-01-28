FROM node:21-alpine3.18

WORKDIR /opt/app
COPY . .


RUN npm install && npm run build
EXPOSE 3000

CMD ["npm", "run", "dev"]

