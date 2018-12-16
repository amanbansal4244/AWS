package com.aws;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DeclineOrderHandler implements RequestHandler<DeclineOrderInput, DeclineOrderOutput>{

	public DeclineOrderOutput handleRequest(DeclineOrderInput i, Context cntxt)
	{
		DeclineOrderOutput o = new DeclineOrderOutput();

			o.setDeclineOrder(true);
			o.setPid(i.getPid());
		
		cntxt.getLogger().log("Decline Order:"+ i.getPid());
		return o;
	}
}	