package com.orgabor.client;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

import com.orgabor.Heartbeater;
import com.orgabor.Message;

public class Listener implements Runnable {
	private Socket clientSocket;
	private ObjectInputStream input;
	private Message message;
	
	Listener(Socket socket) {
		this.clientSocket = socket;
	}

	@Override
	public void run() {
		Thread heartbeat = new Thread(new ClientHeartbeater(clientSocket));
		heartbeat.setDaemon(true);
		heartbeat.start();

		try {
			while(heartbeat.isAlive()) {
				receive();
				if(!message.isPing()) {
					ChatmanClient.clientController.printMessage(message.getMessageText());
				}
			}

		} catch (SocketException e) {
			System.out.println("Client: The socket was closed: " + e.getMessage());		
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found: " + e.getMessage());
		} catch (EOFException e) {	
			System.out.println("Listener input failed: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException on Listener: " + e.getMessage());
		}
	}
	
	private synchronized void receive() throws SocketException, EOFException,
											   IOException, ClassNotFoundException {
		
		input = new ObjectInputStream(clientSocket.getInputStream());
		message = (Message) input.readObject();
	}
	
	
	private class ClientHeartbeater extends Heartbeater {

		public ClientHeartbeater(Socket socket) {
			super(socket);
		}

		@Override
		public void end() {
			try {
				ChatmanClient.clientController.printMessage("Connection lost");
				input.close();
				Client.getInstance().closeConnections();
				
			} catch (IOException e) {
				System.out.println("Closing Listener connections: " + e.getMessage());
			} catch (NullPointerException e) {
				System.out.println("Closing Listener connections: " + e.getMessage());
			}
		}
	}
}
