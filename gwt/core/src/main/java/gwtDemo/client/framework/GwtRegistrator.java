package gwtDemo.client.framework;

/**
 * Registrator's are used to register the types used in each project 
 * that other projects might depend on or needs to specialize
 */
public abstract class GwtRegistrator {
    protected GwtFactory factory;

    public void setFactory(GwtFactory factory) {
        this.factory = factory;
    }

    public abstract void register();
    
    public <PC extends PageController<P>, P extends Page> void registerPage(String pageUrl, final Class<P> pageType, final Class<PC> controllerType) {
    	factory.get(NavigationManager.class).registerHandler(pageUrl, new PageRegistration<PC, P>() {
			@Override
			public PC createPageController(P page) {
				return factory.get(controllerType);
			}
			
			@Override
			public boolean isSingleton() {
				return false;
			}

			@Override
			public Class<P> getPageType() {
				return pageType;
			}
    	});
    }
    
    public <PC extends PageController<P>, P extends Page> void registerSingletonPage(String pageUrl, final Class<P> pageType, final Class<PC> controllerType) {
    	factory.get(NavigationManager.class).registerHandler(pageUrl, new SingletonPageRegistration<PC, P>(pageType) {
			@Override
			public PC createPageController(P page) {
				return factory.get(controllerType);
			}
    	});
    }

    public <BASE extends GwtLogic, EXTENDER extends BASE> void register(Class<BASE> baseClass, GwtCreator<EXTENDER> creator){
        factory.register(baseClass, creator);
    }
}
