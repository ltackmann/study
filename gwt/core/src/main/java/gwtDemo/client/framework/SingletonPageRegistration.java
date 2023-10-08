package gwtDemo.client.framework;

public abstract class SingletonPageRegistration<PC extends PageController<P>, P extends Page> implements PageRegistration<PC, P> {
	protected final Class<P> pageType; 
	
	public SingletonPageRegistration(Class<P> pageType) {
		this.pageType = pageType;
	}
	
	@Override
	public Class<P> getPageType() {
		return pageType;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}