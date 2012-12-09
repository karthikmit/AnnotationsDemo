package com.msghndl.tst;

import junit.framework.Assert;

import org.junit.Test;

import com.msghndl.src.MessageProcessor;

public class basicHelloWorldMessageHandling {

	@Test
	public void handleEchoMessage() throws Exception {
		String message = "echoEND" + "hello world";
		
		MessageProcessor handler = new MessageProcessor();
		String result = handler.process(message);
		
		Assert.assertEquals("hello world", result);
	}
	
	@Test
	public void handleCharCountMessage() throws Exception {
		String message = "charcountEND" + "helloworld";
		MessageProcessor handler = new MessageProcessor();
		String result = handler.process(message);
		
		Assert.assertEquals("10", result);
	}
	
	@Test
	public void handleToUpperCaseMessage() throws Exception {
		String message = "toupperEND" + "hello world";
		MessageProcessor handler = new MessageProcessor();
		String result = handler.process(message);
		
		Assert.assertEquals("HELLO WORLD", result);
	}
}
