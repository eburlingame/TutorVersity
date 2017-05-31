var moment = require('moment');
var uuid = require('node-uuid');
var AWS = require('aws-sdk');
var db = new AWS.DynamoDB();
var bcrypt = require('bcrypt-nodejs');
var auther = require('./checkAuth');

const SALT_ROUNDS = 10;
const USER_TABLE_NAME = "tutorv-users";
const QUESTIONS_TABLE_NAME = "tutorv-questions";

exports.newQuestion = function(payload, cb) {
  console.log(payload);

  auther.auth(payload, function(userResult) {
    if (userResult.success) {
      var user = userResult.user;
      var newQId = uuid.v1();
      var params = {
        Item: {
          "id": {
            S: newQId
          },
          "email": {
            S: user.email
          },
          "subject": {
            S: payload.subject
          },
          "question": {
            S: payload.question
          },
          "latitude": {
            N: "" + payload.latitude
          },
          "longitude": {
            N: "" + payload.longitude
          }
        },
        TableName: QUESTIONS_TABLE_NAME
      };
      console.log(params);
      db.putItem(params, function(err, data) {
        if (err) {
          cb(err);
        } else {
          console.log("data", data);
          cb({
            "message": "Question successfully added " + newQId
          });
        }
      });
    }
    else
    {
      cb({"success": false, "message": "Could not authenticate user with supplied token."})
    }
  });
}