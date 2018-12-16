package com.aws;

public class ReceiveOrderOutput {

	boolean inventoryExists;
	int pid;
	
	public boolean isInventoryExists() {
		return inventoryExists;
	}
	public void setInventoryExists(boolean inventoryExists) {
		this.inventoryExists = inventoryExists;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	
}
