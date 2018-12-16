package com.aws;

public class DeclineOrderOutput {
	
	boolean declineOrder;
	int pid;
	public boolean isDeclineOrder() {
		return declineOrder;
	}
	public void setDeclineOrder(boolean declineOrder) {
		this.declineOrder = declineOrder;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	
}
