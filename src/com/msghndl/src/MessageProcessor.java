package com.msghndl.src;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

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
		
		Reflections reflections = new Reflections("com.msghndl.handler");
		Set<Class<?>> hndlClasses = reflections.getTypesAnnotatedWith(com.msghndl.annotations.Handler.class);
		
		for (Class<?> hndlclass : hndlClasses) {
			Annotation[] clsAnns = hndlclass.getAnnotations();
			
			for (Annotation annotation : clsAnns) {
				if(annotation instanceof Handler) {
					commandHandlerMap.put(((Handler)annotation).value(), (MessageHandler) hndlclass.newInstance());
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
