package gwtDemo.client.framework;

import gwtDemo.client.resource.i18n.ClientMessages;
import gwtDemo.client.service.LanguageService;
import gwtDemo.client.service.LanguageServiceAsync;
import gwtDemo.client.service.UserServiceAsync;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class AppModule extends AbstractGinModule {
	protected void configure() {
		// infrastructure
		bind(ClientSession.class).toProvider(ClientSessionProvider.class);
		bind(ClientMessages.class).in(Singleton.class);
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(LanguageServiceAsync.class).to(LanguageService.class).in(Singleton.class);
		bind(NavigationManager.class).toProvider(NavigationManagerProvider.class);
		// services
		bind(UserServiceAsync.class).in(Singleton.class);
	}
}

