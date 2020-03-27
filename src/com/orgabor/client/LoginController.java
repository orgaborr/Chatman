package com.orgabor.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
	private TextField usernameField;
	private String username;
	
	@FXML
	void selectUsername() {		
		if(!usernameField.getText().trim().equals("")) {
			username = usernameField.getText();
			if(checkUsername()) {
				updateUsername();
			} else {
				//add label message "Username already in use!"
			}
			LoginWindow.loginStage.close();
		}
	}
	
	boolean checkUsername() {
		
	}
	
	void updateUsername() {
		Platform.runLater(() -> {
			ChatmanClient.clientController.setUsername(username);
			ChatmanClient.clientController.printMessage("Connected to server as " + username);		
		});
	}
}
