package com.aws;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SendNotificationHandler implements RequestHandler<SendNotificationInput, SendNotificationOutput>{

	public SendNotificationOutput handleRequest(SendNotificationInput i, Context cntxt)
	{
		SendNotificationOutput o = new SendNotificationOutput();

			o.setSendNotification(true);
			o.setPid(i.getPid());
		
		cntxt.getLogger().log("Send Notification:"+ i.getPid());
		return o;
	}
}	