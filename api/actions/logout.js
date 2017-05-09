var moment = require('moment');
var uuid = require('node-uuid');
var AWS = require('aws-sdk');
var db = new AWS.DynamoDB();

const USER_TABLE_NAME = "tutorv-users";

exports.logout = function(payload, cb) {
  console.log("payload:", payload);

  var params = {
    "TableName": USER_TABLE_NAME,
    "ExpressionAttributeNames": {
      "#T": "token"
    },
    "ExpressionAttributeValues": {
      ":t": {
        S: "inactive"
      }
    },
    "Key": {
      "email": {
        S: payload.email
      }
    },
    UpdateExpression: "SET #T = :t",
    ReturnValues: "ALL_NEW"
  };

  db.updateItem(params, function(err, data) {
    if (err) {
      cb(err);
    }
    else {
      cb({
        "status": "success",
        "message": payload.email + " logged out"
      });
    }
  });

};