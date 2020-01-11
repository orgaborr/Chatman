package com.orgabor.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatmanClient extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			primaryStage.setTitle("Chatman");
			Parent root = FXMLLoader.load(getClass().getResource("ChatmanClient.fxml"));
			Scene scene = new Scene(root, 400, 300);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	

}
