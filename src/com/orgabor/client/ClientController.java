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
		Client.getInstance().listen();
		printMessage("Connected to server");
		printMessage("test message");
	}
	
	@FXML
	void printMessage(String text) {
		text = chatTextArea.getText() + "\n" + text;
		chatTextArea.setText(text);
	}
}
