package com.orgabor.server;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatmanServer extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(ServerController.class.getResource("ChatmanServer.fxml"));
			primaryStage.setTitle("Chatman Server");
			primaryStage.setScene(new Scene(root,300,400));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		Server.getInstance().stopServer();
	}
}
