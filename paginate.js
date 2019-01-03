const async = require("async");
const _ = require("underscore");
const AWS = require("aws-sdk");
AWS.config.update({ region: 'us-west-2' });

const docClient = new AWS.DynamoDB.DocumentClient();

var startKey = [];
var results = []; // to hold result.
var pages = 0;// to store the count of the pages.
//we use do while function from the 'async' module and it takes three functions as an argument.
async.doWhilst(
    /* iteratee function which takes callback as an agrument.
    This is async function , which is always executed at first time and then executed again each time when truth test passes.
    */
    (callback)=>{
        let params = {
            TableName: 'td_notes_test',
            Limit: 3 // dynamodb returns 3 items at a one time.
        };

        /*
        LastEvaluatedKey :  this is simply set of index attribute of the next item uo to which response was returned.
                            So we can use this to get the next set of the data in subsequent/scan query.
        ExclusiveStartKey : we must pass LastEvaluatedKey in subsequent/scan query as a ExclusiveStartKey to get the next page of the data.

        if there is no LastEvaluatedKey in data response that means we have reached the last page of the data.
        */
        if(!_.isEmpty(startKey)) {
            params.ExclusiveStartKey = startKey;
        }

        docClient.scan(params, (err, data)=>{
            if(err) {
                console.log(err);
                callback(err, {});
            } else {
                if(typeof data.LastEvaluatedKey !== 'undefined') {
                    startKey = data.LastEvaluatedKey;
                } else {
                    startKey = [];
                }

                //if data has items then we add into results array with the help of '_.union'
                if(!_.isEmpty(data.Items)){
                    results = _.union(results, data.Items);
                }

                pages++; // increases the pages.

                callback(null, results);
            }
        });
    },

    //truth test : this loop continue to run as long as this function returns a boolean true.
    ()=>{
        if(_.isEmpty(startKey)) {
            return false;
        } else {
            return true;
        }
    },

    /* A callback function which is called after the test function has failed and repeated execution 
    of fn has stopped. callback will be passed an error if one occurred, otherwise null
    */
    (err, data) => {
        if(err) {
            console.log(err);
        } else {
            console.log(data);
            console.log("Item Count", data.length);
            console.log("Pages", pages);
        }
    }
);
