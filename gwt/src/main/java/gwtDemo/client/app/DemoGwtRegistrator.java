package gwtDemo.client.app;

import gwtDemo.client.framework.ClientSession;
import gwtDemo.client.framework.ClientSessionProvider;
import gwtDemo.client.framework.GwtCreator;
import gwtDemo.client.framework.GwtRegistrator;
import gwtDemo.client.framework.GwtSingletonCreator;
import gwtDemo.client.framework.NavigationManager;
import gwtDemo.client.framework.NavigationManagerProvider;
import gwtDemo.client.resource.i18n.ClientMessages;
import gwtDemo.client.service.LanguageService;
import gwtDemo.client.service.LanguageServiceAsync;
import gwtDemo.client.service.UserServiceAsync;

import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class DemoGwtRegistrator extends GwtRegistrator {

	@Override
	public void register() {
		super.register(ClientSession.class, new GwtSingletonCreator<ClientSession>() {
			@Override
			public ClientSession create() {
				return new 
			}});
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
