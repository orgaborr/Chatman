package com.orgabor.server;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatmanServer extends Application {
	static ServerController serverController;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(ServerController.class.getResource("ChatmanServer.fxml"));
			Parent root = loader.load();
			primaryStage.setTitle("Chatman Server");
			primaryStage.setScene(new Scene(root,300,400));		
			primaryStage.show();
			
			serverController = loader.getController();
			primaryStage.setOnCloseRequest(e -> Server.getInstance().closeConnections());

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
