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
	@FXML
	private TextArea textArea;	
	@FXML
	private Label clientCountLabel;
	
	@FXML
	public void initialize() {
		textArea.appendText(TimeTracker.getDate() + "\n");
		Server.getInstance().runServer(5678);
		printMessage("Server initialized");
		
		IntegerBinding clientListSize = Bindings.size((ObservableList<Socket>) Server.getInstance().getClients());
		clientCountLabel.textProperty().bind(clientListSize.asString());		
	} 
	
	@FXML
	void printMessage(String text) {
		textArea.appendText("[" + TimeTracker.getTime() + "] " + text + "\n");
	}
	
	
	
}
