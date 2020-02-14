package com.orgabor.server;

import java.net.Socket;

import com.orgabor.TimeTracker;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ServerController {
	private int portNumber = 5678;
	@FXML
	private TextArea textArea;	
	@FXML
	private Label clientCountLabel;
	
	public void initialize() {
		textArea.appendText(TimeTracker.getDate() + "\n");
		startServer();
		
		IntegerBinding clientListSize = Bindings.size((ObservableList<Socket>) Server.getInstance().getClients());
		clientCountLabel.textProperty().bind(clientListSize.asString());		
	} 
	
	void printMessage(String text) {
		textArea.appendText("[" + TimeTracker.getTime() + "] " + text + "\n");
	}
	
	@FXML
	private void startServer() {
		if(!Server.getInstance().getIsRunning()) {
			Server.getInstance().runServer(portNumber);
			printMessage("Server initialized");
		} else {
			printMessage("Server is already running");
		}
	}
	
	@FXML
	private void terminateServer() {
		if(Server.getInstance().getIsRunning()) {
			Server.getInstance().closeConnections();
			printMessage("Server shutdown");
		} else {
			printMessage("Server is already down");
		}
	}
}
