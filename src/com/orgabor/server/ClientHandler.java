package com.orgabor.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.orgabor.Heartbeater;
import com.orgabor.Message;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Message message;
	
	ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		Server.getInstance().getClients().add(clientSocket);
	}

	@Override
	public void run() {
		try {
			Thread heartbeat = new Thread(new ServerHeartbeater(clientSocket));
			heartbeat.setDaemon(true);
			heartbeat.start();
			
			while(heartbeat.isAlive()) {
				receive();
				if(!message.isPing()) {
					broadcast();
				}			
			}
			
		} catch(SocketException e) {
			System.out.println("ClientHandler: reading input from socket failed. " + e.getMessage());
		} catch(NullPointerException e) {
			System.out.println("ClientHandler: There is no input to read. " + e.getMessage());
		} catch(EOFException e) {
			System.out.println("ClientHandler: EOF while trying to read. " + e.getMessage());
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			System.out.println("Message class not found on reading");
		}
	}
	
	private synchronized void receive() throws SocketException, NullPointerException, EOFException, IOException, ClassNotFoundException {
		input = new ObjectInputStream(clientSocket.getInputStream());
		this.message = (Message) input.readObject();
	}
	
	private void broadcast() throws IOException {
		for(Socket client : Server.getInstance().getClients()) {
			send(client);
		}
	}
	
	private void send(Socket socket) throws IOException {
		this.output = new ObjectOutputStream(socket.getOutputStream());
		output.writeObject(message);
	}
	
	private class ServerHeartbeater extends Heartbeater {

		public ServerHeartbeater(Socket socket) {
			super(socket);
		}

		@Override
		public void end() {
			try {
				Server.getInstance().getClients().remove(clientSocket);
				input.close();
				output.close();
				clientSocket.close();

			} catch(IOException e) {
				System.out.println("Error closing ClientHandler: " + e.getMessage());
			} catch(NullPointerException e) {
				System.out.println("Error closing ClientHandler: " + e.getMessage());
			}
		}
	}
}
