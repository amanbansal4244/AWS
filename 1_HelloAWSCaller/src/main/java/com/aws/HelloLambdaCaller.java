package com.aws;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaAsyncClient;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.google.gson.Gson;

public class HelloLambdaCaller {

	 public static void main(String[] args) {
		 String name = "Student";
		// Define the AWS Region in which the function is to be invoked
		 String region = "us-east-1";
		 
		 if(args.length > 0) {
			 name = args[0];
		 }
		 
		 if(args.length > 1) {
			 region = args[1];
		 }
		 
		 //Create gson object to convert the input object to inform gson
		 Gson gson = new Gson();
		 //Providing created 'helloLambda' profile to AWSLambdaAsyncClient
		 //"HelloLambda" is our lambda name.
		 //Instantiate AWSLambdaClientBuilder to build the Lambda client
		 AWSLambdaAsyncClient asyncClient = 
				 new AWSLambdaAsyncClient(new ProfileCredentialsProvider("HelloLambda")).withRegion(Regions.fromName(region));
		
		 HelloInput helloInput = new HelloInput();
		 helloInput.setName(name);
		 
		 //To invoke lambda function, we have to create invoke request.
		 InvokeRequest invokeRequest = new  InvokeRequest().withFunctionName("HelloLambda").withPayload(gson.toJson(helloInput));
		 
		 //To execute the result, that will invoke the execute result.
		 InvokeResult invokeResult = asyncClient.invoke(invokeRequest);
		 
		 String result = "";
		 
		 //the result contains pay-load which is UTF_8 and  has to convert into string.
		 ByteBuffer byteBuf = invokeResult.getPayload();
	        if (byteBuf != null) {
	             result = StandardCharsets.UTF_8.decode(byteBuf).toString();
	        } 
	        
		// String string =  Charset.forName("UTF-8").decode(invokeResult.getPayload().toString());
				
		 //Now we have gson representation of output of our function as stream, will convert again to our HelloOutput Class.
		 HelloOutput helloOutput = gson.fromJson(result, HelloOutput.class);
		 
		 //helloOutput contains result of our lambda functions.
		 System.out.println("Message :" + helloOutput.getMessage());
		 System.out.println("Function name :" + helloOutput.getFunctionName());
		 System.out.println("Memory Limit :" + helloOutput.getMemoryLimit());
		 
	 }
}
