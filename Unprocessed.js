console.log('Loading function');

var aws = require('aws-sdk');
var dynamo = new aws.DynamoDB();
params = {};

exports.handler = function(sg_event, context) {

    var items = [];
    for(var i = 0; i < sg_event.length; i++) {
        var obj = sg_event[i];
        var request = {
            PutRequest: {
                Item: {
                    email: { S: obj.email },
                    timestamp: { S: obj.timestamp.toString() },
                    sg_message_id: { S: obj.sg_message_id },
                    event: { S: obj.event }
                }
            }
        };
        items.push(request);
    }

    params = {
        RequestItems: {
            sendgrid_response: items
        }
    }

    do {
        dynamo.batchWriteItem( params, function(err, data) {
            if(err)
                context.fail(err);
            else
                params.RequestItems = data.UnprocessedItems;
        });
    } while(!isEmpty(params.RequestItems));
};

function isEmpty(obj) {
    return (Object.keys(obj).length === 0);
}
