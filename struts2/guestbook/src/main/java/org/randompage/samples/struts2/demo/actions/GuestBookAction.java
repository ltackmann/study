package org.randompage.samples.struts2.demo.actions;

import java.util.Date;
import java.util.Vector;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.randompage.samples.struts2.demo.domain.GuestBook;
import org.randompage.samples.struts2.demo.domain.Message;

import com.opensymphony.xwork2.ActionSupport;

public class GuestBookAction extends ActionSupport {
	private static final long serialVersionUID = -8577843349235520003L;
	private String guest;
	private String message;

	@Override
	public String execute() {
		Message msg = new Message();
		msg.setGuest(guest);
		msg.setMessage(message);
		msg.setWhen(new Date());
		GuestBook book = new GuestBook();
		book.addMessage(msg);
		return SUCCESS;
	}

	@SkipValidation
	public String list() {
		return "list";
	}

	public Vector<Message> getMessages() {
		GuestBook book = new GuestBook();
		return book.getMessages();
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}