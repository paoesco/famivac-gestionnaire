'use strict';
module.exports = function(app) {
  
  let famillesServices = require('../familles/familles-services');
  app.route('/familles')
  .get(famillesServices.get);

};
