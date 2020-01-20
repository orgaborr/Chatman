package com.orgabor.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.orgabor.Message;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		try {
			this.input = new ObjectInputStream(
						 new BufferedInputStream(
							 clientSocket.getInputStream()
							 ));
			this.output = new ObjectOutputStream(
						  new BufferedOutputStream(
							  clientSocket.getOutputStream()
							  ));
			
		} catch(IOException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void run() {
		boolean isRunning = true;

		try {
			System.out.println("CliendHandler started");
			while(isRunning) {	
				if(input.available() > 0) {
					Message message = (Message) input.readObject();
					output.writeObject(message);
				}
				
				if(!clientSocket.isConnected()) {
					isRunning = false;
					stop();	
				}
			}

		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			System.out.println("Class not found: " + e.getMessage());
		}

	}
	
	private void stop() throws IOException {
		input.close();
		output.close();
		clientSocket.close();
	}
}
