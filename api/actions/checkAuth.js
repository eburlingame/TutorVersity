var moment = require('moment');
var uuid = require('node-uuid');
var AWS = require('aws-sdk');
var db = new AWS.DynamoDB();
var bcrypt = require('bcrypt-nodejs');

const USER_TABLE_NAME = "tutorv-users";

function mapToUser(userData)
{
  var user = userData.Items[0];
  return {
    "email": user.email.S,
    "name": user.name.S,
    "type": user.type.N,
    "bio": user.bio.S,
    "subjects": user.subjects.S
  }
}

exports.auth = function(payload, cb) {
  console.log(payload);

  var params = {
    "TableName": USER_TABLE_NAME,
    "Limit": 1,
    "ExpressionAttributeNames": {
      "#T": "token"
    },
    "ExpressionAttributeValues": {
      ":v1": {
        S: payload.email
      },
      ":v2": {
        S: payload.token
      }
    },
    KeyConditionExpression: "email = :v1",
    FilterExpression: "#T = :v2"
  };

  db.query(params, function(err, userData) {

      if (err) {
        cb({"success": false, "error": err});
      }
      else {
        if ( userData.Items.length == 0 )
        {
          cb({
            "success": false, 
            "message": "User not found with that token"
          });
          return;
        }
        var user = mapToUser(userData);
        cb({"success": true, "user": user});
    }
  });
};