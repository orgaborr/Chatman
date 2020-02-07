package com.orgabor.client;

import com.orgabor.TimeTracker;

import javafx.beans.property.SimpleStringProperty;
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
		
		if(Client.getInstance().connect("localhost", 5678)) {
			printMessage("System: Connected to server");
		} else {
			printMessage("System: Connection failed");
		}
		
	}
	
	@FXML
	void printMessage(String text) {
		chatTextArea.appendText(TimeTracker.getTime() + " " + text + "\n");
	}

	@FXML
	void sendMessage() {
		if(!messageField.getText().equals("")) {
			Client.getInstance().send();
			ChatmanClient.clientController.getMessageField().setText("");
		}
	}
	
	TextField getMessageField() {
		return messageField;
	}
	
	
}
