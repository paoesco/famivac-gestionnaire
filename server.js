'use strict';


let famillesServices = require('./familles/familles-services');

let express = require('express');
let app = express();
let port = process.env.PORT || 3000;

app.listen(port);

app.route('/familles')
  .get(famillesServices.get);


console.log('FAMIVAC Gestionnaire RESTful API server started on: ' + port);

module.exports = app; // for testing