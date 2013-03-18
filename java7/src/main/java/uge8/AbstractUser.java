package uge8;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractUser<T> {
	@SuppressWarnings("unchecked")
	public Class<T> returnedClass() {
		return (Class<T>) ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}
}
