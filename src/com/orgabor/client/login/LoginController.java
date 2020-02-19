package com.orgabor.client.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
	private String username;
	@FXML
	private TextField usernameField;
	@FXML
	private Button loginButton;
	
	@FXML
	public String getUsername() {
		if(!usernameField.getText().trim().equals("")) {
			username = usernameField.getText();
			LoginWindow.loginStage.close();
		}
		
		return username;
	}
}
