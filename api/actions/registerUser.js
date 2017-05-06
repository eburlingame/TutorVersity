var moment = require('moment');
var uuid = require('node-uuid');
var AWS = require('aws-sdk');
var db = new AWS.DynamoDB();

const SALT_ROUNDS = 10;
const USER_TABLE_NAME = "tutorv-users";

exports.registerUser = function(event, cb) {
  console.log("payload:", JSON.stringify(event));

  bcrypt.hash(myPlaintextPassword, saltRounds, function(err, hash) {
    var params = {
      Item: {
        "email": {
          S: event.parameters.email
        },
        "password_hash": {
          S: hash
        }
      },
      TableName: 
    };

    db.scan(params, function(err, data) {
      if (err) {
        cb(err);
      } else {
        console.log("data", data);
        var res = {
          "body": data.Items.map(mapUserItem)
        };

        if (data.LastEvaluatedKey !== undefined) {
          res.headers = {
            "next": data.LastEvaluatedKey.uid.S
          };
        }
        cb(res);
      }
    });
  });


};