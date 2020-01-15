package com.orgabor.server;

import javafx.fxml.FXML;

public class ServerController {
	
	@FXML
	public static void initialize() {
		Server.startServer(5678);
	}
}
