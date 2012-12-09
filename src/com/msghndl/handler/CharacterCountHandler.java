package com.msghndl.handler;

import com.msghndl.annotations.Handler;

@Handler("charcount")
public class CharacterCountHandler implements MessageHandler {

	@Override
	public String handle(String message) {
		return Integer.toString(message.length());
	}

}
