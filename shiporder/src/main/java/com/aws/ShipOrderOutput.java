package com.aws;

public class ShipOrderOutput {
	
	boolean shipOrder;
	int pid;
	public boolean isShipOrder() {
		return shipOrder;
	}
	public void setShipOrder(boolean shipOrder) {
		this.shipOrder = shipOrder;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
}
