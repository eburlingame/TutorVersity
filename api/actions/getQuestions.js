var moment = require('moment');
var AWS = require('aws-sdk');
var db = new AWS.DynamoDB();
var bcrypt = require('bcrypt-nodejs');
var auther = require('./checkAuth');

const QUESTIONS_TABLE_NAME = "tutorv-questions";

exports.getQuestions = function(payload, cb) {
  console.log(payload);

  auther.auth(payload, function(userResult) {

    if (userResult.success) {
      var user = userResult.user;

      var params = {
      ExpressionAttributeNames: {
          "AT": "AlbumTitle", 
          "ST": "SongTitle"
        }, 
      ExpressionAttributeValues: {
        ":s" {
          L: user.subjects
        }
      }, 
        FilterExpression: "Artist = :a", 
        TableName: "Music"
      };

      db.scan(params, function(err, data) {
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