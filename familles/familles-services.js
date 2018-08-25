'use strict';

const Famille = require('./famille');

const db = [];

exports.get = function (req, res) {
  let kalel = new Famille(1, 'Kalel');
  db.push(kalel);
  let jorel = new Famille(2, 'Jorel');
  db.push(jorel);
  res.json(db);
};

exports.post = function(req, res) {
  let newFamille = new Famille(req.body.id, req.body.name);
  db.push(newFamille);
  res.status(201).json(newFamille);
}