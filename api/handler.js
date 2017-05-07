'use strict';

const registerUser = require('./actions/registerUser.js');
const auth = require('./actions/auth.js');
const logout = require('./actions/logout.js');

function getPayload(event) {
  if (typeof event.body === "object")
  {
    return event.body;
  }
  else {
    return JSON.parse(event.body);
  }
}

function successResponse(data, cb) {
  const response = {
    statusCode: 200,
    body: JSON.stringify(data)
  };

  cb(null, response);
}

module.exports.registerUser = (event, context, cb) => registerUser.registerUser(
  getPayload(event),
  responseData => {
    successResponse(responseData, cb);
  });


module.exports.auth = (event, context, cb) => auth.auth(getPayload(event), responseData => {
  successResponse(responseData, cb);
});

module.exports.logout = (event, context, cb) => logout.logout(getPayload(event), responseData => {
  successResponse(responseData, cb);
});

module.exports.test = (event, context, cb) => {
  const response = {
    statusCode: 200,
    headers: {
      "Access-Control-Allow-Origin": "*", // Required for CORS support to work
      "Access-Control-Allow-Credentials": true // Required for cookies, authorization headers with HTTPS 
    },
    body: JSON.stringify(event)
  };

  cb(null, response);
};