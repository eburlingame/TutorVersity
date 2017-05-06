var moment = require('moment');
var uuid = require('node-uuid');
var AWS = require('aws-sdk');
var db = new AWS.DynamoDB();
var bcrypt = require('bcrypt');

const SALT_ROUNDS = 10;
const USER_TABLE_NAME = "tutorv-users";

exports.auth = function(event, cb) {
  console.log("payload:", JSON.stringify(event));

var params = {
    "TableName": USER_TABLE_NAME,
    "Limit": 1

	ExpressionAttributeValues: {
		":v1": {
			S: event.parameters.email
		}
	}, 
	KeyConditionExpression: "email = :v1", 
  };

  db.query(params, function(err, data) {
    if (err) 
    {
      cb(err);
    } 
    else
    {
      console.log("data", data);



      var res = {
        "body": data.Items.map(mapUserItem)
      };
      cb(res);
    }
  });
  
};