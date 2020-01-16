package com.orgabor;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeTracker {
	
	public static String getTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		return dateFormat.format(Calendar.getInstance().getTime());
	}
	
	public static String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(Calendar.getInstance().getTime());
	}
}
