package com.orgabor.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
	private TextField usernameField;
	private String username;
	private String serverResponse;
	
	@FXML
	void selectUsername() {		
		if(!usernameField.getText().trim().equals("")) {
			username = usernameField.getText();
//			if(checkUsername()) {
				updateUsername();
//			} else {
//				username = "";
//				//add label message "Username already in use!"
//			}
			LoginWindow.loginStage.close();
		}
	}
	
	private boolean checkUsername() {
		Client.getInstance().send(username);
		while(serverResponse.equals("")) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("checkUsername interrupted while waiting for responce: " + e.getMessage());
			}
		}
		if(serverResponse.equals("Username " + username + " accepted")) {
			return true;
		}
		return false;
	}
	
	private void updateUsername() {
		Platform.runLater(() -> {
			Client.getInstance().setUsername(username);
			ChatmanClient.clientController.printMessage("Connected to server as " + username);		
		});
	}
	
	void setServerResponse(String response) {
		this.serverResponse = response;
	}
}
