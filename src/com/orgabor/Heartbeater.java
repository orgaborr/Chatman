package com.orgabor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Heartbeater implements Runnable {
	private Socket socket;
	private boolean running = true;
	
	Heartbeater(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		while(running) {
			try {
				Thread.sleep(10000);
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				output.writeObject(new Message(""));

			} catch (IOException e) {
				System.out.println("Connection lost");
				running = false;
				break;
			} catch (InterruptedException e) {
				System.out.println("Hearbeater woken up: " + e.getMessage());
			}
		}
	}
}

