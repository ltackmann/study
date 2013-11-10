package gwtDemo.client.framework;

import gwtDemo.client.framework.api.ClientSession;
import gwtDemo.client.framework.api.ClientSessionProvider;
import gwtDemo.client.framework.api.NavigationManager;
import gwtDemo.client.framework.api.NavigationManagerProvider;
import gwtDemo.client.resource.i18n.ClientMessages;
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
		bind(NavigationManager.class).toProvider(NavigationManagerProvider.class);
		// services
		bind(UserServiceAsync.class).in(Singleton.class);
	}
}

