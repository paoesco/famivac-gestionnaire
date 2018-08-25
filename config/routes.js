'use strict';
module.exports = function(app) {
  
  const famillesServices = require('../familles/familles-services');
  app.route('/familles')
  .get(famillesServices.get)
  .post(famillesServices.post);

};
