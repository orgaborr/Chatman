package com.orgabor.client;

import com.orgabor.TimeTracker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientController {
	@FXML
	private TextArea chatTextArea;
	@FXML
	private TextField messageField;
	@FXML
	private Button sendButton;
	
	public void initialize() {
		chatTextArea.appendText(TimeTracker.getDate() + "\n");
		tryConnect("localhost", 5678);
	}
	
	void tryConnect(String ip, int port) {
		if(Client.getInstance().connect(ip, port)) {
			printMessage("Connected to server");
		} else {
			printMessage("Connection failed");
		}
	}
	
	void printMessage(String text) {
		chatTextArea.appendText("[" + TimeTracker.getTime() + "] " + text + "\n");
	}
	
	@FXML
	void sendMessage() {
		if(!messageField.getText().equals("")) {
			if(!Client.getInstance().send(messageField.getText())) {
				printMessage("Server is down");
			};
			messageField.setText("");
		}
	}
}
