//During the test the env variable is set to test
process.env.NODE_ENV = 'test';

//Require the dev-dependencies
let chai = require('chai');
let chaiHttp = require('chai-http');
let server = require('../../server/server');
let should = chai.should();

chai.use(chaiHttp);

describe('GET /familles', () => {
  it('it should GET all the familles', (done) => {
    chai.request(server)
    .get('/familles')
    .end((err, res) => {
      res.should.have.status(200);
      res.body.should.be.a('array');
      res.body.length.should.be.eql(2);
      done();
    });
  });
});

describe('POST /familles', () => {
  it('it should POST a new famille', (done) => {
    let postObject = { id: 3, name: 'Clark'};
    chai.request(server)
    .post('/familles')
    .set('content-type', 'application/json')
    .send(postObject)
    .end((err, res) => {
      res.should.have.status(201);
      res.body.should.be.a('object');
      res.body.should.be.eql(postObject);
      done();
    })
  })
})