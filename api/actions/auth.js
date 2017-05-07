var moment = require('moment');
var uuid = require('node-uuid');
var AWS = require('aws-sdk');
var db = new AWS.DynamoDB();
var bcrypt = require('bcrypt-nodejs');

const SALT_ROUNDS = 10;
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
    "ExpressionAttributeValues": {
      ":v1": {
        S: payload.email
      }
    },
    KeyConditionExpression: "email = :v1",
  };

  console.log(params);

  db.query(params, function(err, userData) {

      if (err) {
        cb(err);
      }
      else {
        if ( userData.Items.length == 0)
        {
          cb({
            "success": false, 
            "message": "Username not found"
          });
          return;
        }
        var user = mapToUser(userData);
        bcrypt.compare(payload.password, userData.Items[0].password_hash.S, function(err, res) {

            if (err || !res) {
              cb({
                "success": false,
                "message": "Password incorrect."
              });
            }
            else {
              var loggedInToken = uuid.v1();
              var params = {
                "TableName": USER_TABLE_NAME,
                "ExpressionAttributeNames": {
                  "#T": "token"
                },
                "ExpressionAttributeValues": {
                  ":t": {
                    S: loggedInToken
                  }
                },
                "Key": {
                  "email": { S: user.email }
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
                    "token": loggedInToken,
                    "user": user 
                  });
                }
              });

            };
        });
    }

  });

};