package com.ca.listener;

import java.util.ArrayList;
import java.util.List;

public final class MessageHandler {

	private static List<MessageListener> messageListeners = new ArrayList<MessageListener>();
	
	private MessageHandler(){
		
	}
	
	public static void add(MessageListener messageListener) {
		messageListeners.add(messageListener);
	}
	
	public static void remove(MessageListener messageListener){
		messageListeners.remove(messageListener);
	}

	public static List<MessageListener> getMessageListeners() {
		return messageListeners;
	}

	public static void setMessageListeners(List<MessageListener> messageListeners) {
		MessageHandler.messageListeners = messageListeners;
	}
	
	public static void handle(long userId){
		
		for (MessageListener mesLis : messageListeners) {
			
			if (mesLis.getUserId() == userId) {
				
				mesLis.handleMessage();
			}
			
		}
		
	}
	
	
	
}
