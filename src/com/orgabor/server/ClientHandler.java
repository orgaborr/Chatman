package com.orgabor.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.orgabor.Heartbeater;
import com.orgabor.Message;

import javafx.application.Platform;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Message message;
	
	private int clientId = Server.getInstance().getClientId();
	
	ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		
		Runnable updateClientMap = () -> ((Map<Integer, Socket>) Server.getInstance().getClients()).put(clientId, clientSocket);
		Platform.runLater(updateClientMap);
		
		Server.getInstance().setNextClientId(clientId+1);
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
			System.out.println("ClientHandler: There is no input to read: " + e.getMessage());
		} catch(EOFException e) {
			System.out.println("ClientHandler: EOF while trying to read: " + e.getMessage());
		} catch(IOException e) {
			System.out.println("ClientHandler IOException: " + e.getMessage());;
		} catch(ClassNotFoundException e) {
			System.out.println("Message class not found on reading");
		}
	}
	
	private synchronized void receive() throws SocketException, NullPointerException, EOFException,
											   IOException, ClassNotFoundException {
		
		input = new ObjectInputStream(clientSocket.getInputStream());
		this.message = (Message) input.readObject();
	}
	
	private void broadcast() throws IOException {
		Iterator<Entry<Integer, Socket>> clientIterator = Server.getInstance().
														  getClients().entrySet().iterator();
		while(clientIterator.hasNext()) {
			Socket s = clientIterator.next().getValue();
			send(s);
		}
		clientIterator.remove();
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
				Runnable removeClient = () -> {
					((Map<Integer, Socket>) Server.getInstance().getClients()).remove(clientId);
					ChatmanServer.serverController.printMessage("Client " + clientId + " disconnected");
				};
				Platform.runLater(removeClient);
				
				input.close();
				output.close();
				clientSocket.close();

			} catch(IOException e) {
				System.out.println("IOException on closing ClientHandler: " + e.getMessage());
			} catch(NullPointerException e) {
				System.out.println("NullPointerException on closing ClientHandler.");
			}
		}
	}
}
