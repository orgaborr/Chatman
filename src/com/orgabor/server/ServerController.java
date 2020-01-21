package com.orgabor.server;

import com.orgabor.TimeTracker;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ServerController {
	@FXML
	private TextArea textArea;
	
	@FXML
	public void initialize() {
		Server.getInstance().runServer(5678);
		textArea.setText(TimeTracker.getDate() + "\n" +
						 TimeTracker.getTime() + " Server initialized");
	}
}
