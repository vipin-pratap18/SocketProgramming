package com.socketsimulation.services;

import java.util.Map;

import com.socketsimulation.model.ConnectionData;

public class POSTServices {

	private Map<Integer, ConnectionData> connectionMap;
	
	public POSTServices(Map<Integer, ConnectionData> connectionMap) {
		this.connectionMap = connectionMap;
	}

	@SuppressWarnings("deprecation")
	public String killConnection(ConnectionData data){
		connectionMap.remove(data.getConnectionId());
		return "{stat:killed}";
	}
}
