package com.aws;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ReceiverOrderHandler implements RequestHandler<ReceiveOrderInput, ReceiveOrderOutput>{

	public ReceiveOrderOutput handleRequest(ReceiveOrderInput i, Context cntxt)
	{
		ReceiveOrderOutput o = new ReceiveOrderOutput();

		if(i.quantity < 5) {
			o.setInventoryExists(true);
			o.setPid(i.getPid());
		}else {
			o.setInventoryExists(false);
			o.setPid(i.getPid());
		}
		
		cntxt.getLogger().log("Order Received:"+"Product:"+i.pid+" ,Quantity:"+i.quantity);
		return o;
	}
}