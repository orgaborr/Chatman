package com.orgabor.client;

import com.orgabor.TimeTracker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientController {
	private String ip = "localhost";
	private int port = 5678;
	
	private String username;
	
	@FXML
	private TextArea chatTextArea;
	@FXML
	private TextField messageField;
	@FXML
	private Button sendButton;
	
	public void initialize() {
		chatTextArea.appendText(TimeTracker.getDate() + "\n");
		tryToConnect();
	}
	
	@FXML
	void tryToConnect() {
		if(Client.getInstance().connect(ip, port)) {
			tryToLogin();
			printMessage("Connected to server as " + username);
		} else {
			printMessage("Connection failed");
		}
	}
	
	void tryToLogin() {
		try {
			LoginWindow.openLoginWindow();
			System.out.println("Username: " + username);
		} catch(Exception e) {
			System.out.println("Exception on login: " + e.getMessage());
		}	
	}
	
	void printMessage(String text) {
		chatTextArea.appendText("[" + TimeTracker.getTime() + "] " + text + "\n");
	}
	
	@FXML
	void sendMessage() {
		if(!messageField.getText().trim().equals("")) {
			if(!Client.getInstance().send(messageField.getText().trim())) {
				printMessage("Server is down");
			};
			messageField.setText("");
		}
	}
	
	void setUsername(String username) {
		this.username = username;
	}
	
	
}
