package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ChatmanServer extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Chatman Server");
			Parent root = FXMLLoader.load(getClass().getResource("ChatmanServer.fxml"));
			Scene scene = new Scene(root,300,400);
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
