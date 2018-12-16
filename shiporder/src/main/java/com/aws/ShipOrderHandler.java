package com.aws;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ShipOrderHandler implements RequestHandler<ShipOrderInput, ShipOrderOutput>{

	public ShipOrderOutput handleRequest(ShipOrderInput i, Context cntxt)
	{
		ShipOrderOutput o = new ShipOrderOutput();

			o.setShipOrder(true);
			o.setPid(i.getPid());
		
		cntxt.getLogger().log("Ship order:"+ i.getPid());
		return o;
	}
}	