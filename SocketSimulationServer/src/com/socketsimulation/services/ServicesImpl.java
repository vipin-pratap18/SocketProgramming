package com.socketsimulation.services;

import java.util.Map;

import com.socketsimulation.model.ConnectionData;

public class ServicesImpl implements Services{

	private Map<Integer, ConnectionData> connectionMap;
	
	public ServicesImpl(Map<Integer, ConnectionData> connectionMap) {
		this.connectionMap = connectionMap;
	}

	
	@Override
	public String GET(ConnectionData obj) {
		
		GETServices get = new GETServices(connectionMap);
		String response = null;
		if("sleep".equals(obj.getOperation())){
			response = get.sleepConnection(obj);
		}
		else if("server-status".equals(obj.getOperation())){
			response = get.serverStatus(obj);
		}
		else
			response = "{status : This GET service is not implemented yet}";
		return response;
	}

	@Override
	public String POST(ConnectionData obj) {
		
		POSTServices post = new POSTServices(connectionMap);
		String response = null;
		if("sleep".equals(obj.getOperation())){
			response = post.killConnection(obj);
		}else
			response = "{status : This POST service is not implemented yet}";
		
		return response;
	}

	@Override
	public String startService(ConnectionData conn) {
		
		String response = null;
		if("GET".equals(conn.getConncetionType())){
			response = GET(conn);
		}else if("POST".equals(conn.getConncetionType())){
			response = POST(conn);
		}else{
			response = "{status : This request type not implemented yet}";
			
		}
		return response;
	}

}
