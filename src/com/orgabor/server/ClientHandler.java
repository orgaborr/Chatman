package com.orgabor.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.orgabor.Message;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	private boolean isRunning;
	
	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		try {
			this.input = new ObjectInputStream(clientSocket.getInputStream());
			this.output = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch(IOException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void run() {
		isRunning = true;

		while(isRunning) {
			try {
				Message message = (Message) input.readObject();
				
				message.setTimeSent(getServerTime());
				
				output.writeObject(message);
				
				if(!clientSocket.isConnected()) {
					isRunning = false;
					stop();	
				}
				
			} catch(IOException e) {
				e.printStackTrace();
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static String getServerTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		return dateFormat.format(Calendar.getInstance().getTime());
	}
	
	private void stop() throws IOException {
		input.close();
		output.close();
		clientSocket.close();
	}
}
