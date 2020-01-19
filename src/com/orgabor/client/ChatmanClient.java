package com.orgabor.client;

import com.orgabor.server.Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatmanClient extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(ClientController.class.getResource("ChatmanClient.fxml"));
			primaryStage.setTitle("Chatman");
			primaryStage.setScene(new Scene(root, 400, 300));
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(e -> Client.getInstance().closeConnections());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	

}
