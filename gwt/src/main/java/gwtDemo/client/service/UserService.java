package gwtDemo.client.service;

import gwtDemo.shared.domain.User;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("users.rpc")
public interface UserService extends RemoteService {
	User authenticate(String email, String password);
}
