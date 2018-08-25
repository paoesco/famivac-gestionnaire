'use strict';

let express = require('express');

// Configures server
let app = express();
let port = process.env.PORT || 3000;
// Sets routes
let routes = require('./routes');
routes(app);
// Starts server
app.listen(port);
console.log('FAMIVAC Gestionnaire RESTful API server started on: ' + port);

module.exports = app; // for testing