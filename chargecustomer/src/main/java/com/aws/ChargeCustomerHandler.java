package com.aws;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ChargeCustomerHandler implements RequestHandler<ChargeCustomerInput, ChargeCustomerOutput>{

	public ChargeCustomerOutput handleRequest(ChargeCustomerInput i, Context cntxt)
	{
		ChargeCustomerOutput o = new ChargeCustomerOutput();

			o.setChargeCustomer(true);
			o.setPid(i.getPid());
		
		cntxt.getLogger().log("Charge Customer:"+ i.getPid());
		return o;
	}
}	