			var AWS = require("aws-sdk");
			require("aws-sdk-stepfunctions");
			
			var stepfunctions = new AWS.StepFunctions();
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
			    s3.getObject(params, (err, data) => {
			        if (err) {
			            const message = 'Error getting object ${key} from bucket ${bucket}. Make sure they exist and your bucket is in the same region as this function.';
			            console.log(message);
			            callback(message);
			            
			        } else {
				/*we are parsing the data content into JSON object.
				*/
			                var OrderJson = JSON.parse(data.Body.toString());
			
				// getting content from file and creating one param variable with state machine function and content
				/*Note : we can have 1000+ records in file , so we just need to parse into JSON object in above line.
				Have array instead of variable 'wf_params' and inside traverse the content and store into array
				
				STATE_MACHINE_ARN : this should be exact ARN of STATE_MACHINE
				*/
			                var wf_params = {
			                  stateMachineArn: 'STATE_MACHINE_ARN', /* required */
			                  input: '{"pid":'+OrderJson.pid+',"quantity":'+OrderJson.quantity+'}',
			                  name: 'Execution_Lambda_'+OrderJson.pid
			                };
			
				// calling step function using above param varaible.
			                stepfunctions.startExecution(wf_params, function(err, data) {
			                  if (err) {
			                      console.log(err, err.stack);
			                      callback(null, err);
			                  }
			                  else{
			                      console.log(data);
			                      callback(null, data);
			                  }     
			            });
			        }
			    });
			
			
};