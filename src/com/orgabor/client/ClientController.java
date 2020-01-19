package com.orgabor.client;

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
		new Client("localhost", 5678);
		printMessage("Connected to server");
	}
	
	@FXML
	void printMessage(String text) {
		chatTextArea.appendText(text + "\n");
	}
}
