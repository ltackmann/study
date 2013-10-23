package gwtDemo.client;

import gwtDemo.client.resource.i18n.ClientMessages;
import gwtDemo.client.service.UserServiceAsync;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class AppModule extends AbstractGinModule {
	protected void configure() {
		bind(ClientMessages.class).in(Singleton.class);
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(UserServiceAsync.class).in(Singleton.class);
	}
}
