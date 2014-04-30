package gwtDemo.client.framework;

import com.google.web.bindery.event.shared.EventBus;

public interface GwtFactory {
	/**
	 * Register type in the factory 
	 * 
	 * @param clazz
	 * @param creator
	 */
    <T extends GwtLogic, K extends T> void register(Class<T> clazz, GwtCreator<K> creator);

    /**
     * Get instance of type
     * 
     * @param clazz
     * @return
     */
    <T extends GwtLogic> T get(Class<T> clazz);
    
    /**
     * Easy access to GWT EventBus
     */
    EventBus getEventBus();
	
	// hack needed to allow unit tests to mock the version of GwtFactory returned by current
    static class Get {
    	protected static GwtFactory INSTANCE;
    	
    	public static GwtFactory current() {
    		return INSTANCE;
    	}
    	public static void setCurrent(GwtFactory fac){
    		INSTANCE = fac;
    	}
    }
}
