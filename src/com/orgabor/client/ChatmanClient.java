package com.orgabor.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatmanClient extends Application {
	static ClientController clientController; 
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(ClientController.class.getResource("ChatmanClient.fxml"));
			Parent root = loader.load();
			primaryStage.setTitle("Chatman");
			primaryStage.setScene(new Scene(root, 400, 300));
			primaryStage.show();
			
			clientController = loader.getController();
			primaryStage.setOnCloseRequest(e -> Client.getInstance().closeConnections());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	

}
