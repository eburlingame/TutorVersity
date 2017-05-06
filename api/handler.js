'use strict';

const todo = require('./todo.js');

module.exports.getUsers = (event, context, cb) => todo.getUsers({
  parameters: {
    // limit: parseInt(event.queryStringParameters.limit),
    // next: event.queryStringParameters.next
  }
}, responseData => {
	const response = {
      statusCode: 200,
      body: JSON.stringify(responseData.body)
    };

    cb(null, response);
});

module.exports.test = (event, context, cb) => {
	const response = {
      statusCode: 200,
      headers: {
        "Access-Control-Allow-Origin" : "*", // Required for CORS support to work
        "Access-Control-Allow-Credentials" : true // Required for cookies, authorization headers with HTTPS 
      },
      body: JSON.stringify({ 
      	"message":"This is a test!", 
	    "limit": event 
      })
    };

    cb(null, response);
};
