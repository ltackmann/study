package gwtDemo.client.app;

import gwtDemo.client.framework.ClientSession;
import gwtDemo.client.framework.ClientSessionCreator;
import gwtDemo.client.framework.GwtRegistrator;

public class DemoGwtRegistrator extends GwtRegistrator {

	@Override
	public void register() {
		super.register(ClientSession.class, new ClientSessionCreator());
		// infrastructure
		//		bind(ClientSession.class).toProvider(ClientSessionProvider.class);
		//		bind(ClientMessages.class).in(Singleton.class);
		//		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		//		bind(LanguageServiceAsync.class).to(LanguageService.class).in(Singleton.class);
		//		bind(NavigationManager.class).toProvider(NavigationManagerProvider.class);
				// services
		//		bind(UserServiceAsync.class).in(Singleton.class);
		//	}
	}
	
}
