package com.orgabor.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private BufferedReader input;
	private PrintWriter output;
	
	private boolean isRunning;
	
	private String inputLine;
	private String outputLine;
	
	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		try {
			this.input = new BufferedReader(
	   			   	     new InputStreamReader(
		   				     clientSocket.getInputStream()));
			this.output = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch(IOException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void run() {
		isRunning = true;

		while(isRunning) {
			try {
				inputLine = input.readLine();
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(!clientSocket.isConnected()) {
				stop();
				isRunning = false;
			}
		}
	}
	
	private void stop() {
		try {
			input.close();
			output.close();
			clientSocket.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
