package org.randompage.samples.struts2.demo.domain;

import java.util.Vector;

public class GuestBook {
	private static Vector<Message> messages;

	public void addMessage(Message msg) {
		if (messages == null) {
			messages = new Vector<Message>();
		}
		messages.add(msg);
	}

	public Vector<Message> getMessages() {
		return messages;
	}

}