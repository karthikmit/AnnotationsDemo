package com.msghndl.handler;

import com.msghndl.annotations.Handler;

@Handler("echo")
public class EchoMessageHandler implements MessageHandler {

	@Override
	public String handle(String message) {
		return message;
	}

}
