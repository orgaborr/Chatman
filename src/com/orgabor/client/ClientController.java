package com.orgabor.client;

import com.orgabor.TimeTracker;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientController {
	@FXML
	private TextArea chatTextArea;
	@FXML
	private TextField messageField;
	
	public void initialize() {
		chatTextArea.appendText(TimeTracker.getDate() + "\n");
		tryToConnect();
	}
	
	@FXML
	void tryToConnect() {
		if(Client.getInstance().connect()) {
			tryToLogin();
		} else {
			printMessage("Connection failed");
		}
	}
	
	static void tryToLogin() {
		try {
			LoginWindow.openLoginWindow();
		} catch(Exception e) {
			System.out.println("Exception on login: " + e.getMessage());
		}	
	}
	
	void printMessage(String text) {
		chatTextArea.appendText("[" + TimeTracker.getTime() + "] " + text + "\n");
	}
	
	@FXML
	void sendMessage() {
		String msgText = messageField.getText().trim();
		if(!msgText.equals("")) {
			if(!Client.getInstance().send(msgText)) {
				printMessage("Server is down");
			};
			messageField.setText("");
		}
	}
}
