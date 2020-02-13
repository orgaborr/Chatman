package com.orgabor.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class ChatmanClient extends Application {
	static ClientController clientController;

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(ClientController.class.getResource("ChatmanClient.fxml"));
			Parent root = loader.load();
			primaryStage.setTitle("Chatman");
			
			Scene primaryScene = new Scene(root, 400, 300);
			primaryStage.setScene(primaryScene);
			primaryStage.show();
			
			clientController = loader.getController();
			primaryScene.setOnKeyPressed(e -> {
				if(e.getCode().equals(KeyCode.ENTER) ) {
					clientController.sendMessage();
				}
			});
			
			primaryStage.setOnCloseRequest(e -> Client.getInstance().closeConnections());	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	

}
