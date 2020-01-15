package com.orgabor.server;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ServerController {
	@FXML
	private TextArea textArea;
	
	@FXML
	public void initialize() {
		textArea.setText("Server initialized");
		Server.getInstance().startServer(5678);
	}
}
