package com.orgabor.server;

import com.orgabor.TimeTracker;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ServerController {
	@FXML
	private TextArea textArea;
	
	@FXML
	public void initialize() {
		textArea.appendText(TimeTracker.getDate() + "\n");
		Server.getInstance().runServer(5678);
		printMessage("Server initialized");
	}
	
	@FXML
	void printMessage(String text) {
		textArea.appendText(TimeTracker.getTime() + " " + text + "\n");
	}
}
