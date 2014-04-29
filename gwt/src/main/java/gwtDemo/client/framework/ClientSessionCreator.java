package gwtDemo.client.framework;

public class ClientSessionCreator extends GwtSingletonCreator<ClientSession> {
    public static ClientSession INSTANCE;
	
    @Override
	public ClientSession create() {
		return INSTANCE;
	}
}
