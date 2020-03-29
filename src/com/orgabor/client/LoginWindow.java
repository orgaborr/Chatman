package com.orgabor.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

class LoginWindow {
	static Stage loginStage;
	static LoginController loginController;
	
	static void openLoginWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("LoginWindow.fxml"));
			Parent root = loader.load();
			loginStage = new Stage();
			loginStage.setTitle("Login");
			loginController = loader.getController();
			
			Scene loginScene = new Scene(root);
			loginStage.setScene(loginScene);
			loginStage.showAndWait();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
