package com.socketsimulation.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketSimClient {
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		
		Socket socket = new Socket("localhost", 9000);
		
		OutputStream out = socket.getOutputStream();
		//out.write("GET/sleep?timeout=20&connid=1".getBytes());
		out.write("GET/sleep?timeout=20&connid=1".getBytes());
		out.flush();
		out.close();

		socket.close();
		
		Socket socket1 = new Socket("localhost", 9000);
		InputStream input = socket1.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		String line = null;
		StringBuilder responseData = new StringBuilder();
		
		while((line = in.readLine()) != null) {
			responseData.append(line);
		}

		System.out.println("Response is : " + responseData);
		in.close();
		socket1.close();
		
	}

}
