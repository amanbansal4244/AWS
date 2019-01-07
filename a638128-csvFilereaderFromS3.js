var AWS = require("aws-sdk");
require("aws-sdk-stepfunctions");
//const csv = require('csvtojson');
//const csv = require('fast-csv');
var fs = require('fs')
// var fast_csv = require('fast-csv')
 const csv = require('csv-parser');

var stepfunctions = new AWS.StepFunctions(); // initiate stepfunction 
var s3 = new AWS.S3();




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

// now asking to S3 to access the object with bucket name and file name.
//Fetch or read data from aws s3
    s3.getObject(params, (err, data) => {
        if (err) {
            const message = 'Error getting object ${key} from bucket ${bucket}. Make sure they exist and your bucket is in the same region as this function.';
            console.log(message);
            callback(message);
            
        } else {
            
            fs.createReadStream(key)
            .pipe(csv())
            .on('data', function(data){
                console.log(data);
            })
            .on('data', function(data){
                console.log('Read Finsihed');
        });

        }
    });


};

