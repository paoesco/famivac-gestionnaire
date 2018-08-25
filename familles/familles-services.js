'use strict';

let Famille = require('./famille');

exports.get = function (req, res) {

  let kalel = new Famille(1, 'Kalel');
  let jorel = new Famille(2, 'Jorel');
  let familles = [
  kalel,
  jorel
  ]
  res.json(familles);
};