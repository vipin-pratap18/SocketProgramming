package com.socketsimulation.model;

public class ConnectionData {
	
	private String conncetionType;
	private int timeout;
	private int connectionId;
	private String operation;
	
	
	public String getConncetionType() {
		return conncetionType;
	}
	public void setConncetionType(String conncetionType) {
		this.conncetionType = conncetionType;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public int getConnectionId() {
		return connectionId;
	}
	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}
	
	public boolean equals(Object obj){
		ConnectionData c;
		if(obj instanceof ConnectionData){
			c = (ConnectionData)obj;
			return this.getConnectionId() == c.getConnectionId();
		}
		else 
			return false;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}


}
