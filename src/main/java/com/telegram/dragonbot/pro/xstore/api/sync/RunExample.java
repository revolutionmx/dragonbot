package com.telegram.dragonbot.pro.xstore.api.sync;

import com.telegram.dragonbot.pro.xstore.api.sync.ServerData.ServerEnum;

public class RunExample {

	//please change the login and password below
	private static String LOGIN = "";
	private static String PASSWORD = "";
	
	//please provide the application details if you received them
	private static String APP_ID = null;
	private static String APP_NAME = null;

	public static void main(String[] args) throws Exception {
		Example ex = new Example();
		Credentials credentials = new Credentials(LOGIN, PASSWORD, APP_ID, APP_NAME);
		ex.runExample(ServerEnum.DEMO, credentials);
	}
}