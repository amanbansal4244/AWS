package com.aws;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateOrderStatusHandler implements RequestHandler<UpdateOrderStatusInput, UpdateOrderStatusOutput>{

	public UpdateOrderStatusOutput handleRequest(UpdateOrderStatusInput i, Context cntxt)
	{
		UpdateOrderStatusOutput o = new UpdateOrderStatusOutput();

			o.setUpdateOrderStatus(true);
			o.setPid(i.getPid());
		
		cntxt.getLogger().log("Update Order Status:"+ i.getPid());
		return o;
	}
}	