package com.orgabor.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.orgabor.Heartbeater;
import com.orgabor.Message;
import com.orgabor.TimeTracker;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Message message;
	private Heartbeater hb;
	
	ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;	
	}

	@Override
	public void run() {
		boolean isRunning = true;
		new Thread(hb).start();
		
		try {
			System.out.println(TimeTracker.getTime() + " CliendHandler started");
			Server.getInstance().getClients().add(clientSocket);
			
			while(isRunning) {
				receive();
				for(Socket client : Server.getInstance().getClients()) {
					send(client);
				}
				
				if(!hb.isRunning()) {
					isRunning = false;
					stop();
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
	
	private void receive() throws SocketException, NullPointerException, EOFException, IOException, ClassNotFoundException {
		this.input = new ObjectInputStream(clientSocket.getInputStream());
		this.message = (Message) input.readObject();
	}
	
	private void send(Socket socket) throws IOException {
		this.output = new ObjectOutputStream(socket.getOutputStream());
		output.writeObject(message);
	}
	
	private void stop() throws IOException {
		Server.getInstance().getClients().remove(clientSocket);
		input.close();
		output.close();
		clientSocket.close();
	}
}
