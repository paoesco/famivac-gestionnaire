- TravisCI : [![Build Status](https://travis-ci.org/paoesco/famivac-gestionnaire.svg)](https://travis-ci.org/paoesco/famivac-gestionnaire)
- VersionEye : [![Dependency Status](https://www.versioneye.com/user/projects/558ea3f6316338001e000073/badge.svg?style=flat)](https://www.versioneye.com/user/projects/558ea3f6316338001e000073)
- Coveralls : [![Coverage Status](https://coveralls.io/repos/paoesco/famivac-gestionnaire/badge.svg?branch=master&service=github)](https://coveralls.io/github/paoesco/famivac-gestionnaire?branch=master)
- Codacy : [![Codacy Badge](https://api.codacy.com/project/badge/grade/14eb0f1c48d64909a87beddc0d2afb89)](https://www.codacy.com/app/pao-esco/famivac-gestionnaire)

The OpenShift `jbossas` cartridge documentation can be found at:

https://github.com/openshift/origin-server/tree/master/cartridges/openshift-origin-cartridge-jbossas/README.md

## Startup

- VM Options : -Xms128m -Xmx512m -DMAIL_SERVER_USERNAME="username" -DMAIL_SERVER_PASSWORD="password"

## OpenShift deployment

- Add environment variable for mail server : rhc env set MAIL_SERVER_USERNAME="<Value>" MAIL_SERVER_PASSWORD="<Value2>" -a gestionnaire

