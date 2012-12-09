package com.msghndl.handler;

import com.msghndl.annotations.Handler;

@Handler("toupper")
public class ToUpperCaseHandler implements MessageHandler {

	@Override
	public String handle(String message) {
		return message.toUpperCase();
	}

}
