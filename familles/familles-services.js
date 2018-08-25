'use strict';


exports.get = function (req, res) {
	let familles = [
	{
		id: 1,
    name: "Kalel"
	},
  {
    id: 2,
    name: "Jorel"
  }
	]
	res.json(familles);
};