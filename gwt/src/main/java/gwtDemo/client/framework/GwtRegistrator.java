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

    public <BASE extends GwtLogic, EXTENDER extends BASE> void register(Class<BASE> baseClass, GwtCreator<EXTENDER> creator){
        factory.register(baseClass, creator);
    }
}
