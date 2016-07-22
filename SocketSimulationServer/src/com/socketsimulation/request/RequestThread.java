package com.socketsimulation.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.socketsimulation.model.ConnectionData;
import com.socketsimulation.services.ServicesImpl;

public class RequestThread implements Runnable{

	private Socket clientSocket;
	private Map<Integer, ConnectionData> connectionMap;

	public RequestThread(Socket clientSocket, Map<Integer, ConnectionData> connectionMap){
		this.clientSocket = clientSocket;
		this.connectionMap = connectionMap;
	}

	@Override
	public void run() {

		String responseData = null;
		OutputStream out;
		try{
			InputStream input = clientSocket.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			String line = null;
			StringBuilder requestData = new StringBuilder();
			
			while((line = in.readLine()) != null) {
				requestData.append(line);
			}

			ConnectionData data = parseRequest(requestData.toString());
			if(data.getConnectionId() == 0){
				data.setConnectionId(createConnectionID(connectionMap));
				connectionMap.put(data.getConnectionId(), data);
			}
			responseData = new ServicesImpl(connectionMap).startService(data);
			out = clientSocket.getOutputStream();

			out.write(responseData.getBytes());
			out.flush();
			out.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
			try {
				out = clientSocket.getOutputStream();
				out.write("stat:killed".getBytes());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		finally{
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}


	public ConnectionData parseRequest(String request){

		ConnectionData connectionData = new ConnectionData();

		if(request.contains("?")){
			String[] st = request.split("\\?");
			String[] requstOp = st[0].split("/");
			connectionData.setConncetionType(requstOp[0]);
			connectionData.setOperation(requstOp[1]);
			String[] params = st[1].split("&");
			for(String param : params){
				if(param.contains("timeout"))
					connectionData.setTimeout(Integer.parseInt(param.replaceAll("timeout=", "")));

				if(param.contains("connid"))
					connectionData.setConnectionId(Integer.parseInt(param.replace("connid=", "")));
			}

		}else{
			String[] st = request.split("/");
			connectionData.setConncetionType(st[0]);
			connectionData.setOperation(st[1]);

		}
		return connectionData;
	}

	public int createConnectionID(Map<Integer, ConnectionData> connectionMap){
		Set<Integer> set = connectionMap.keySet();
		List<Integer> list = new ArrayList<>();
		list.addAll(set);
		Collections.sort(list);
		return list.size() + 1;
	}

}
