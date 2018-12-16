package com.aws;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class NotifyProcurementHandler implements RequestHandler<NotifyProcurementInput, NotifyProcurementOutput>{

	public NotifyProcurementOutput handleRequest(NotifyProcurementInput i, Context cntxt)
	{
		NotifyProcurementOutput o = new NotifyProcurementOutput();

			o.setNotifyProcurement(true);
			o.setPid(i.getPid());
		
		cntxt.getLogger().log("Notify Procurement:"+ i.getPid());
		return o;
	}
}	