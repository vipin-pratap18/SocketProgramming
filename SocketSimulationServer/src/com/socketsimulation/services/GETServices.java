package com.socketsimulation.services;

import java.util.Map;

import com.socketsimulation.model.ConnectionData;

public class GETServices {

	private Map<Integer, ConnectionData> connectionMap;
	
	public GETServices(Map<Integer, ConnectionData> connectionMap) {
		this.connectionMap = connectionMap;
	}

	//will return Map<Integer, Integer> as Object
	public String serverStatus(ConnectionData data){
		StringBuffer serverStatus = new StringBuffer();
		serverStatus.append("{");
		for(Integer i : connectionMap.keySet()){
			serverStatus.append("connid="+i+ ":timeout="+connectionMap.get(i).getTimeout()+",");
		}
		serverStatus.append("}");
		return serverStatus.toString();
	}
	
	
	@SuppressWarnings("static-access")
	public String sleepConnection(ConnectionData data){
		try {
			Thread.currentThread().sleep(data.getTimeout());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "{stat:ok}";
	}
}
