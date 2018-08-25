'use strict';

const express = require('express');
const bodyParser = require('body-parser');

// Configures server
const app = express();
app.use(bodyParser.json()); // for parsing application/json
app.use(bodyParser.urlencoded({ extended: true })); // for parsing application/x-www-form-urlencoded
const port = process.env.PORT || 3000;
// Sets routes
const routes = require('./routes');
routes(app);
// Starts server
app.listen(port);
console.log('FAMIVAC Gestionnaire RESTful API server started on: ' + port);

module.exports = app; // for testing