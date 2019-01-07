
var fs = require('fs')
require("aws-sdk-stepfunctions");

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
  
    // read CSV with csv-parser
    var otherOptions = {
      columns : true,
      auto_parse : true,
      escape : '\\',
      trim : true,
    };
    var parser = parse(otherOptions);
    parser.on('data', function(data) {
      data.id = uuidv4();
      data.createDate = new Date().toISOString();
      console.log(data);
    })
    .on('end',function(data) {
      //do something wiht csvData
      console.log(data);
    });
    s3Stream.pipe(parser);
  };