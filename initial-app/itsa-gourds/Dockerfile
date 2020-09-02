FROM alpine:3.7

WORKDIR .
RUN apk add --update nodejs nodejs-npm
RUN npm install -g http-server
COPY /dist /dist
COPY boot.sh ./
RUN chmod +x boot.sh

RUN apk add dos2unix
RUN dos2unix boot.sh

EXPOSE 8080
ENTRYPOINT ["./boot.sh"]
