# Famivac - Gestionnaire

- TravisCI : [![Build Status](https://travis-ci.org/paoesco/famivac-gestionnaire.svg)](https://travis-ci.org/paoesco/famivac-gestionnaire)
- Coveralls : [![Coverage Status](https://coveralls.io/repos/paoesco/famivac-gestionnaire/badge.svg?branch=master&service=github)](https://coveralls.io/github/paoesco/famivac-gestionnaire?branch=master)
- Codacy : [![Codacy Badge](https://api.codacy.com/project/badge/grade/14eb0f1c48d64909a87beddc0d2afb89)](https://www.codacy.com/app/pao-esco/famivac-gestionnaire)

## Contribute

- Java 11
- Java EE 8
- Thorntail
- Create postgresql database with user `gestionnaire`, password `gestionnaire`, database `gestionnaire`
- See Startup section for any environment variables

## Startup

Environment properties :

- swarm.mail.smtp.host
- swarm.mail.smtp.port
- swarm.mail.mail-sessions.famivac.smtp-server.username
- swarm.mail.mail-sessions.famivac.smtp-server.password
- swarm.mail.mail-sessions.famivac.smtp-server.ssl
- swarm.ds.name
- swarm.ds.connection.url
- swarm.ds.username
- swarm.ds.password
- swarm.https.only

Startup line

java 
    -Dswarm.ds.name=PostgreSQLDS 
    -Dswarm.ds.connection.url=$JDBC_DATABASE_URL 
    -Dswarm.ds.username=$JDBC_DATABASE_USERNAME 
    -Dswarm.ds.password=$JDBC_DATABASE_PASSWORD 
    -Djavamelody.datasources=java:jboss/datasources/PostgreSQLDS 
    -Dswarm.mail.smtp.host=$MAIL_SERVER_SMTP_HOST 
    -Dswarm.mail.smtp.port=$MAIL_SERVER_SMTP_PORT 
    -Dswarm.mail.mail-sessions.famivac.smtp-server.username=$MAIL_SERVER_SMTP_USERNAME 
    -Dswarm.mail.mail-sessions.famivac.smtp-server.password=$MAIL_SERVER_SMTP_PASSWORD 
    -Dswarm.mail.mail-sessions.famivac.smtp-server.ssl=true
    -Dswarm.http.port=$PORT 
    -jar gestionnaire-web/target/gestionnaire-web-*-swarm.jar

## Heroku deployment

- Add environment variables
