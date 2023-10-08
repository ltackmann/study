package gwtDemo.client.framework;

public abstract class GwtSingletonCreator<T extends GwtLogic> extends GwtCreator<T> {
    private T instance;

    @Override
    T get() {
        if(instance == null){
            instance = create();
        }
        return instance;
    }

    @Override
    public abstract T create();
}
