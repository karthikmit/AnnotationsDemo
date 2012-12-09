package com.msghndl.src;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import com.msghndl.annotations.Handler;
import com.msghndl.handler.MessageHandler;

public class MessageProcessor {

	private Map<String, MessageHandler> commandHandlerMap = new HashMap<String, MessageHandler>();

	public MessageProcessor() throws Exception {
		try {
			makeCommandHandlerMap();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void makeCommandHandlerMap() throws ClassNotFoundException, 
									InstantiationException, IllegalAccessException {
		// Idea is to go through all Handlers at run-time and fill this up.
		
		// TODO This array should be populated automatically using reflections rather than hard-coding.
		String[] messageHandlerClasses = {"com.msghndl.handler.EchoMessageHandler",
											"com.msghndl.handler.CharacterCountHandler",
											"com.msghndl.handler.ToUpperCaseHandler"};
		
		for (String hndlclass : messageHandlerClasses) {
			Annotation[] clsAnns = Class.forName(hndlclass).getAnnotations();
			
			for (Annotation annotation : clsAnns) {
				if(annotation instanceof Handler) {
					commandHandlerMap.put(((Handler)annotation).value(), (MessageHandler) Class.forName(hndlclass).newInstance());
				}
			}
		}
		
	}

	public String process(String message) {
		String command = message.split("END")[0];
		
		MessageHandler commandHandler = commandHandlerMap.get(command);
		return commandHandler.handle(message.split("END")[1]);
	}

}
