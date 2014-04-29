package gwtDemo.client.framework;

/**
 * GWT does not support reflection through class.getInstance, GwtCreator is solves this 
 * as serving as a factory for each registered type
 */
public abstract class GwtCreator<T extends GwtLogic> {
    abstract T get();

    public abstract T create();
}