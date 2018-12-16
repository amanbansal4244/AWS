package com.aws;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateInventoryHandler implements RequestHandler<UpdateInventoryInput, UpdateInventoryOutput>{

	public UpdateInventoryOutput handleRequest(UpdateInventoryInput i, Context cntxt)
	{
		UpdateInventoryOutput o = new UpdateInventoryOutput();

			o.setUpdateInventory(true);
			o.setPid(i.getPid());
		
		cntxt.getLogger().log("Update Inventory:"+ i.getPid());
		return o;
	}
}	