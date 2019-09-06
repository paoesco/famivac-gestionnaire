#!/bin/bash
eval "$(ssh-agent -s)" #start the ssh agent
chmod 600 deploy/deploy.key # this key should have push access
ssh-add deploy/deploy.key
ssh-keyscan ns377672.ip-37-59-60.eu >> ~/.ssh/known_hosts
git remote add deploy dokku@ns377672.ip-37-59-60.eu:famivac-gestionnaire-app-prod
git config --global push.default simple
git push deploy master:master
