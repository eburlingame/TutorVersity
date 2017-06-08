var moment = require('moment');
var AWS = require('aws-sdk');
var db = new AWS.DynamoDB();
var bcrypt = require('bcrypt-nodejs');
var auther = require('./checkAuth');

const QUESTIONS_TABLE_NAME = "tutorv-questions";

function mapQuestionsList(data)
{
  return data["Items"].map(function(q){
    return {
      "subject": q.subject.S,
      "question": q.question.S,
      "id": q.id.S,
      "email": q.email.S,
      "latitude": q.latitude.N,
      "longitude": q.longitude.N,
    }
  })
}

exports.getUserQuestions = function(payload, cb) {
  console.log(payload);

  auther.auth(payload, function(userResult) {

    if (userResult.success) {
      var user = userResult.user;

      var params = {
        ExpressionAttributeNames: {
          "#e": "email"
        }, 
        ExpressionAttributeValues: {
          ":e": {S: user.email}
        },
        FilterExpression: "#e = :e", 
        TableName: QUESTIONS_TABLE_NAME
      };

      db.scan(params, function(err, data) {
        if (err) {
          cb(err);
        } else {
          console.log("data", data);
          cb({
            "questions": mapQuestionsList(data)
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