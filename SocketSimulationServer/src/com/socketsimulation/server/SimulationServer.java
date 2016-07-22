package com.socketsimulation.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.socketsimulation.model.ConnectionData;
import com.socketsimulation.request.RequestThread;

public class SimulationServer {

	public static void main(String[] args) throws IOException{
		Map<Integer, ConnectionData> connectionMap = new ConcurrentHashMap<>();
		System.out.println("Server started and listing to port : " +9000);
		ServerSocket serverSocket = new ServerSocket(9000);
		boolean isStopped = false;
		while(!isStopped){

			Socket clientSocket = serverSocket.accept();
			RequestThread request = new RequestThread(clientSocket, connectionMap);
			Thread requestThread = new Thread(request);
			requestThread.start();

		}
		serverSocket.close();
	}

}

