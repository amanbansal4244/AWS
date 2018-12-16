package com.aws;

public class UpdateOrderStatusOutput {
	
	boolean updateOrderStatus;
	int pid;
	public boolean isUpdateOrderStatus() {
		return updateOrderStatus;
	}
	public void setUpdateOrderStatus(boolean updateOrderStatus) {
		this.updateOrderStatus = updateOrderStatus;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	
}
