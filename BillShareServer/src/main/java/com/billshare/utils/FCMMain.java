/*package com.billshare.utils;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;


public class FCMMain {

	public static void main(String[] args) {
		String serverKey = "AIzaSyBf62shZODxrI6btQrzLbV4LPQu9CQblaM";
	    Thread t = new Thread() {
	        public void run(){ 
	            try {
	                Sender sender = new FCMSender(serverKey);
	                Message message = new Message.Builder()
	                                  .collapseKey("message")
	                                  .timeToLive(3)
	                                  .delayWhileIdle(true)
	                                  .addData("message", "Notification from Java application")
	                                  .build();  

	                // Use the same token(or registration id) that was earlier
	                // used to send the message to the client directly from
	                // Firebase Console's Notification tab.
	                com.google.android.gcm.server.Result result = sender.send(message,"ebhaYp2eCsM:APA91bFgCM61pH9selxFz66bcDCQoa8vjB_HqkJiykQXha7nKOFyLnuTDMZFGcMQ8_xZOu4XOT2i-AIpiGVAJ3BGhphQM3SBrl-aRIAuAPo8DPQykvn_HetTfHbNQlpyh-dcSZ_vJBuv",1);
	                System.out.println("Result: " + result.toString());
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }};
	        t.start();
	        try {
	            t.join();
	        }
	        catch (InterruptedException iex) {
	            iex.printStackTrace();
	        }
	
	    
	}
}
*/