package gwtDemo.client.framework;

public class SecurityManagerImpl implements SecurityManager {
	@Override
	public boolean canUserViewPage(Class<Page> page) {
		// TODO 
		return true;
	}
}
