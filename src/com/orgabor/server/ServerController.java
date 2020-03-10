package com.orgabor.server;

import java.net.Socket;

import com.orgabor.TimeTracker;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ServerController {
	@FXML
	private TextArea textArea;	
	@FXML
	private Label clientCountLabel;
	
	public void initialize() {
		textArea.appendText(TimeTracker.getDate() + "\n");
		startServer();
		
		IntegerBinding clientMapSize = Bindings.size((ObservableMap<Integer, Socket>) Server.getInstance().getClients());
		clientCountLabel.textProperty().bind(clientMapSize.asString());
	} 
	
	void printMessage(String text) {
		textArea.appendText("[" + TimeTracker.getTime() + "] " + text + "\n");
	}
	
	@FXML
	private void startServer() {
		if(!Server.getInstance().getIsRunning()) {
			Server.getInstance().runServer();
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
