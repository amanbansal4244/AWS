package com.aws;

public class SendNotificationOutput {
	
	boolean sendNotification;
	int pid;
	public boolean isSendNotification() {
		return sendNotification;
	}
	public void setSendNotification(boolean sendNotification) {
		this.sendNotification = sendNotification;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	
	
}
