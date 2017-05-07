var moment = require('moment');
var uuid = require('node-uuid');
var AWS = require('aws-sdk');
var bcrypt = require('bcrypt-nodejs');

var db = new AWS.DynamoDB();

const SALT_ROUNDS = 10;
const USER_TABLE_NAME = "tutorv-users";

exports.registerUser = function(payload, cb) {
  console.log(payload);

  bcrypt.hash(payload.password, null, null, function(err, hash) {
    var params = {
      Item: {
        "email": {
          S: payload.email
        },
        "type": {
          N: String(payload.type)
        },
        "name": {
          S: payload.name
        },
        "bio": {
          S: payload.bio
        },
        "subjects": {
          S: payload.subjects
        },
        "password_hash": {
          S: hash
        }
      },
      TableName: USER_TABLE_NAME
    };

    db.putItem(params, function(err, data) {
      if (err) {
        cb(err);
      }
      else {
        console.log("data", data);
        cb({
          "message": "User successfully added " + payload.email
        });
      }
    });
  });
};