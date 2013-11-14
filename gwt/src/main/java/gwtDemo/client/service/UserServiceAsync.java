package gwtDemo.client.service;

import gwtDemo.shared.domain.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
	void login(String email, String password, AsyncCallback<User> callback);
}
