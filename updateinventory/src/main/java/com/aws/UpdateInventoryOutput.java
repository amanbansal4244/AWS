package com.aws;

public class UpdateInventoryOutput {
	
	boolean updateInventory;
	int pid;
	public boolean isUpdateInventory() {
		return updateInventory;
	}
	public void setUpdateInventory(boolean updateInventory) {
		this.updateInventory = updateInventory;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
}
