package com.orgabor.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

class LoginWindow {
	static Stage loginStage;
	
	static void openLoginWindow() {
		try {
			Parent root = new FXMLLoader(LoginController.class.getResource("LoginWindow.fxml")).load();
			loginStage = new Stage();
			loginStage.setTitle("Login");
			
			Scene loginScene = new Scene(root);
			loginStage.setScene(loginScene);
			loginStage.showAndWait();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
