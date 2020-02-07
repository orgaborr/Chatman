package com.orgabor.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

import com.orgabor.Heartbeater;
import com.orgabor.Message;
import com.orgabor.TimeTracker;

public class Listener implements Runnable {
	private Socket clientSocket;
	private ObjectInputStream input;
	private Message message;
	
	Listener(Socket socket) {
		this.clientSocket = socket;
	}

	@Override
	public void run() {
		System.out.println("Listener started");

		Thread heartbeat = new Thread(new ClientHeartbeater(clientSocket));
		heartbeat.start();


		try {
			while(heartbeat.isAlive()) {
				receive();
				if(!message.isPing()) {
					System.out.println(TimeTracker.getTime() + " Recieving: " + message.getMessageText());
					ChatmanClient.clientController.printMessage(message.getMessageText());
				}
			}

		} catch (SocketException e) {
			System.out.println("Client: The socket was closed: " + e.getMessage());		
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void receive() throws SocketException, IOException, ClassNotFoundException {
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
				input.close();
				Client.getInstance().closeConnections();
				
			} catch (IOException e) {
				System.out.println("Connection lost, closing connections. " + e.getMessage());
			}
		}
	}
	
}
