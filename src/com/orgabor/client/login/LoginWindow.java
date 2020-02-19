package com.orgabor.client.login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginWindow {
	public static LoginController loginController;
	public static Stage loginStage;
	
	public static void openLoginWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("LoginWindow.fxml"));
			Parent root = loader.load();
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
