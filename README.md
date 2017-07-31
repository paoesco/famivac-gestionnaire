# Famivac - Gestionnaire

- TravisCI : [![Build Status](https://travis-ci.org/paoesco/famivac-gestionnaire.svg)](https://travis-ci.org/paoesco/famivac-gestionnaire)
- VersionEye : [![Dependency Status](https://www.versioneye.com/user/projects/558ea3f6316338001e000073/badge.svg?style=flat)](https://www.versioneye.com/user/projects/558ea3f6316338001e000073)
- Coveralls : [![Coverage Status](https://coveralls.io/repos/paoesco/famivac-gestionnaire/badge.svg?branch=master&service=github)](https://coveralls.io/github/paoesco/famivac-gestionnaire?branch=master)
- Codacy : [![Codacy Badge](https://api.codacy.com/project/badge/grade/14eb0f1c48d64909a87beddc0d2afb89)](https://www.codacy.com/app/pao-esco/famivac-gestionnaire)

## Contribute

- Java 8
- Java EE 7
- Wildfly Swarm
- Create postgresql database with user `gestionnaire`, password `gestionnaire`, database `gestionnaire`
- See Startup section for any environment variables

## Startup

Environment properties :

- MAIL_SERVER_USERNAME
- MAIL_SERVER_PASSWORD
- swarm.ds.name
- swarm.ds.connection.url
- swarm.ds.username
- swarm.ds.password

Startup line

-Xms128m -Xmx512m -DMAIL_SERVER_USERNAME="username" -DMAIL_SERVER_PASSWORD="password" -Dswarm.ds.name=PostgreSQLDS -Dswarm.ds.connection.url=jdbc:postgresql://localhost:5432/gestionnaire -Dswarm.ds.username=gestionnaire -Dswarm.ds.password=gestionnaire

## Heroku deployment

- Add environment variables

## License

The MIT License (MIT)

Copyright (c) 2015-2017 Hubesco https://hubesco.com/blog

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

