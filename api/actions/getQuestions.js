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
      "question": q.subject.S,
      "id": q.subject.S,
      "email": q.subject.S,
      "latitude": q.subject.N,
      "longitude": q.subject.N,
    }
  })
}

exports.getQuestions = function(payload, cb) {
  console.log(payload);

  auther.auth(payload, function(userResult) {

    if (userResult.success) {
      var user = userResult.user;

      var subjects = user.subjects.split(/\s?,\s?/);

      var i = 0; 
      var subjectQuery = subjects
        .map(function(item){ return "#sub = :val" + (i++) })
        .join(" or ");

      var expressionValues = {};
      for (var i = 0; i < subjects.length; i++)
      {
        expressionValues[":val" + i] = { S: subjects[i] };
      }
      console.log(subjectQuery);

      var params = {
        ExpressionAttributeNames: {
          "#sub": "subject"
        }, 
        ExpressionAttributeValues: expressionValues,
        FilterExpression: subjectQuery, 
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