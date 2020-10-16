# Famivac - Gestionnaire

- TravisCI : [![Build Status](https://travis-ci.org/famivac/famivac-gestionnaire.svg)](https://travis-ci.org/famivac/famivac-gestionnaire)
- Coveralls : [![Coverage Status](https://coveralls.io/repos/paoesco/famivac-gestionnaire/badge.svg?branch=master&service=github)](https://coveralls.io/github/paoesco/famivac-gestionnaire?branch=master)
- Codacy : [![Codacy Badge](https://api.codacy.com/project/badge/grade/14eb0f1c48d64909a87beddc0d2afb89)](https://www.codacy.com/app/pao-esco/famivac-gestionnaire)

## Contribute

- Java 11
- Java EE 8
- Thorntail
- Create postgresql database with user `gestionnaire`, password `gestionnaire`, database `gestionnaire`
- See Startup section for any environment variables

## Startup

System properties :
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
    -Dswarm.http.port=$PORT 
    -jar gestionnaire-web/target/gestionnaire-web-2.0.0-thorntail.jar


## Dokku

- `dokku postgres:backup-auth famivac-gestionnaire-db-prod AWS_ACCESS_KEY AWS_SECRET_KEY eu-west-3`
- cron : `0 0 * * * dokku postgres:backup famivac-gestionnaire-db-prod famivac-gestionnaire-db-prod`