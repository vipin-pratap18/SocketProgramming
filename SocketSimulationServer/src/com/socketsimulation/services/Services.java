package com.socketsimulation.services;

import com.socketsimulation.model.ConnectionData;

public interface Services {
	public String startService(ConnectionData conn);
	public String GET(ConnectionData conn);
	public String POST(ConnectionData conn);

}
