package gwtDemo.client.framework.api;

import com.google.inject.Provider;

public class ClientSessionProvider implements Provider<ClientSession> {
	public static ClientSession INSTANCE;
	
	@Override
	public ClientSession get() {
		return INSTANCE;
	}
}
