package edu.smu.dataObject;

public class ProcessControlBlock {
	private String processID;
	private int arrivalTime;
	private int burstTime;
	private int priority;
	private int waitTime;
	
	public String getProcessID() {
		return processID;
	}
	public void setProcessID(String processID) {
		this.processID = processID;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public int getBurstTime() {
		return burstTime;
	}
	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	public String toString(){
		return "Process ID: "+getProcessID()
			+", Arrival Time: "+getArrivalTime()
			+", Burst Time: "+getBurstTime()
			+", Priority: "+getPriority();
	}
}
