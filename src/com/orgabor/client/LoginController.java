package com.orgabor.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
	private TextField usernameField;
	
	@FXML
	void updateUsername() {		
		if(!usernameField.getText().trim().equals("")) {
			String username = usernameField.getText();
			Platform.runLater(() -> {
				ChatmanClient.clientController.setUsername(username);
				ChatmanClient.clientController.printMessage("Connected to server as " + username);		
			});
			LoginWindow.loginStage.close();
		}
	}
}
