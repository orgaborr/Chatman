package com.orgabor.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
	String username;
	
	@FXML
	private TextField usernameField;
	@FXML
	private Button loginButton;
	
	@FXML
	void updateUsername() {
		if(!usernameField.getText().trim().equals("")) {
			username = usernameField.getText();
			LoginWindow.loginStage.close();
		}
	}
}
