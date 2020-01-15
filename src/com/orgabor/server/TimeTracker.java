package com.orgabor.server;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeTracker {
	
	static String getCompleteServerTime() {
		return getServerDate() + " " + getServerTime();
	}
	
	static String getServerTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		return dateFormat.format(Calendar.getInstance().getTime());
	}
	
	static String getServerDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		return dateFormat.format(Calendar.getInstance().getTime());
	}
}
