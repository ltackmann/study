package uml;

import java.util.Locale;

import org.omg.PortableServer.LifespanPolicy;

import com.schantz.foundation.config.BootstrapLogic;
import com.schantz.foundation.config.Configurator;
import com.schantz.foundation.context.conversation.ConversationContextManager;
import com.schantz.foundation.context.conversation.DefaultConversationContext;
import com.schantz.foundation.context.request.DefaultRequestContext;
import com.schantz.foundation.context.request.RequestContext;
import com.schantz.foundation.context.request.RequestContextManager;
import com.schantz.foundation.context.session.DefaultSessionContext;
import com.schantz.foundation.context.session.SessionContextManager;
import com.schantz.foundation.dao.PersistenceManager;
import com.schantz.foundation.logic.factory.BaseFactory;
import com.schantz.foundation.text.DefaultTextRepository;
import com.schantz.usermanagement.FoundationUser;

import umldemo.LifeEvent;

public class BootstrapTest {
	public static void main(String[] args) {
		BaseFactory factory = Configurator.getRootFactory();
		factory.get(BootstrapLogic.class);
		setupContexts(factory);
		
		PersistenceManager persistenceManager = getRequestContext().getPersistenceManager();
		LifeEvent event = new LifeEvent();
		persistenceManager.save(event);
		System.err.println(event.getUniqueId());
	}
	
	protected static void setupContexts(BaseFactory factory) {
		SessionContextManager.createAndSetContext(DefaultSessionContext.class);
		ConversationContextManager.createAndSetContext(DefaultConversationContext.class);
		RequestContextManager.createAndSetContext(DefaultRequestContext.class).setUser(new FoundationUser());

		getRequestContext().setTextRepository(factory.get(DefaultTextRepository.class));
		getRequestContext().setLocale(new Locale("da", "DK"));
	}
	
	protected static RequestContext getRequestContext() {
		return RequestContextManager.getContext();
	}
}
