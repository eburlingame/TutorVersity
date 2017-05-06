var moment = require('moment');
var uuid = require('node-uuid');
var AWS = require('aws-sdk');
var db = new AWS.DynamoDB();

function getValue(attribute, type) {
  if (attribute === undefined) {
    return null;
  }
  return attribute[type];
}

function mapTaskItem(item) {
  return {
    "tid": item.tid.N,
    "description": item.description.S,
    "created": item.created.N,
    "due": getValue(item.due, 'N'),
    "category": getValue(item.category, 'S'),
    "completed": getValue(item.completed, 'N')
  };
}

function mapUserItem(item) {
  return {
    "uid": item.uid.S,
    "email": item.email.S
  };
}

exports.getUsers = function(event, cb) {
  console.log("getUsers", JSON.stringify(event));
  var params = {
    "TableName": "todo-user",
    "Limit": event.parameters.limit || 10
  };
  if (event.parameters.next) 
  {
    params.ExclusiveStartKey = 
    {
      "uid": {
        "S": event.parameters.next
      }
    };
  }
  db.scan(params, function(err, data) {
    if (err) {
      cb(err);
    } 
    else
    {
      console.log("data", data);
      var res = {
        "body": data.Items.map(mapUserItem)
      };
      
      if (data.LastEvaluatedKey !== undefined) 
      {
        res.headers = {"next": data.LastEvaluatedKey.uid.S};
      }
      cb(res);
    }
  });
};
