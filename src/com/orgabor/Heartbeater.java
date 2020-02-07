package com.orgabor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public abstract class Heartbeater implements Runnable {
	private Socket socket;
	
	public Heartbeater(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(10000);
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				output.writeObject(new Message("//ping"));

			} catch (IOException e) {
				System.out.println("Heartbeater: Connection lost");
				end();
				break;
			} catch (InterruptedException e) {
				System.out.println("Hearbeater woken up: " + e.getMessage());
			}
		}
	}
	
	public abstract void end();
		
}

