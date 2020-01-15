package com.orgabor.server;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ServerController {
	@FXML
	private TextArea textArea;
	
	@FXML
	public void initialize() {
		new Thread(Server.getInstance()).start();
		textArea.setText("Server initialized");
	}
}
