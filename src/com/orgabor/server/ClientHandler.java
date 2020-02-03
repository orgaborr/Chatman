package com.orgabor.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.orgabor.Message;
import com.orgabor.TimeTracker;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
//		try {
//			
//			
//			System.out.println(TimeTracker.getTime() + " ClientHandler initialized");
//		} catch(SocketException e) {
//			System.out.println(TimeTracker.getTime() + "ClientHandler: Lost connection. " + e.getMessage());
//		} catch(IOException e) {
//			e.printStackTrace();
//		}	
	}

	@Override
	public void run() {
		boolean isRunning = true;
		try {
			System.out.println(TimeTracker.getTime() + " CliendHandler started");
			while(isRunning) {
				this.input = new ObjectInputStream(clientSocket.getInputStream());
				System.out.println("ClientHandler: input received");
				Message message = (Message) input.readObject();
				ChatmanServer.serverController.printMessage("Message received: " + message.getMessageText());
				
				this.output = new ObjectOutputStream(clientSocket.getOutputStream());
				output.writeObject(message);

				if(!clientSocket.isConnected()) {
					isRunning = false;
					stop();	
				}
			}
		} catch(SocketException e) {
			System.out.println("ClientHandler: reading input from socket failed. " + e.getMessage());
		} catch(NullPointerException e) {
			System.out.println("ClientHandler: There is no input to read. " + e.getMessage());
		} catch(EOFException e) {
			System.out.println("ClientHandler: EOF, reading input failed. " + e.getMessage());
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
