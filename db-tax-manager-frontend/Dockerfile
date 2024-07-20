FROM node:18-alpine

WORKDIR /db-tax-manager-app/

COPY public/ /db-tax-manager-app/public
COPY src/ /db-tax-manager-app/src
COPY package.json /db-tax-manager-app/

RUN npm install

CMD ["npm", "start"]


