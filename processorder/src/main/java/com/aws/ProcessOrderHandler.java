package com.aws;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ProcessOrderHandler implements RequestHandler<ProcessOrderInput, ProcessOrderOutput>{

	public ProcessOrderOutput handleRequest(ProcessOrderInput i, Context cntxt)
	{
		ProcessOrderOutput o = new ProcessOrderOutput();

			o.setProcessOrder(true);
			o.setPid(i.getPid());
		
		cntxt.getLogger().log("Processing order:inventoryExists:"+ i.getPid());
		return o;
	}
}	