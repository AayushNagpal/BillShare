package com.billshare.utils;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class FCMUtils {
	private static FCMUtils fcmUtils;
	final static String serverKey = "AIzaSyBf62shZODxrI6btQrzLbV4LPQu9CQblaM";

	private FCMUtils() {

	}

	public static FCMUtils instance() {
		if (fcmUtils == null) {
			fcmUtils = new FCMUtils();
		}
		return fcmUtils;
	}

	public void sendNotification(String deviceKey, String title, String _message) {
		Thread t = new Thread() {
			public void run() {
				try {
					Sender sender = new FCMSender(serverKey);
					Message message = new Message.Builder().collapseKey("message").timeToLive(3).delayWhileIdle(true)
							.addData("title", title).addData("message", _message).build();

					// Use the same token(or registration id) that was earlier
					// used to send the message to the client directly from
					// Firebase Console's Notification tab.
					Result result = sender.send(message, deviceKey, 1);
					System.out.println("Result: " + result.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
		try {
			t.join();
		} catch (InterruptedException iex) {
			iex.printStackTrace();
		}

	}
}
