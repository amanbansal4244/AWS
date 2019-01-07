var fs = require('fs')
require("aws-sdk-stepfunctions");

var async = require('async');

// dependencies
const csv = require('fast-csv');
const parse = require('csv-parser')
const aws = require('aws-sdk');
const s3 = new aws.S3({ apiVersion: '2006-03-01' });
const uuidv4 = require('uuid/v4');


exports.handler = (event, context, callback) => {
    // Get the object from the event and show its content type
    const bucket = event.Records[0].s3.bucket.name;
  //Key represent the file name.
    const key = decodeURIComponent(event.Records[0].s3.object.key.replace(/\+/g, ' '));
    console.log("-------------"+bucket+",----------"+key);
   
    const params = {
        Bucket: bucket,
        Key: key,
    };
    // read S3 object stream
    var s3Stream = s3.getObject(params).createReadStream();
  
    var parser = parse({delimiter: ','}, function (err, data) {
      console.log("data:"+ data);
      async.eachSeries(data, function (line, callback) {
        console.log("dataaaaaa:"+ data);
        console.log("line:"+ line);
        // do something with the line
        doSomething(line).then(function() {
          console.log("dataaaaaa1111:"+ data);
        console.log("line11111:"+ line);
          // when processing finishes invoke the callback to move to the next one
          callback();
        });
      })
    });
    s3Stream.pipe(parser);
  };