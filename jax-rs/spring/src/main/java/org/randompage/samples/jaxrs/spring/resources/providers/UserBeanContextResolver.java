package org.randompage.samples.jaxrs.spring.resources.providers;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

import org.randompage.samples.jaxrs.spring.model.UserBeanList;

import com.sun.jersey.api.json.JSONJAXBContext;

@Provider
@SuppressWarnings("unchecked")
public class UserBeanContextResolver implements ContextResolver<JAXBContext> {

	private JAXBContext context;
	private Class[] types = { UserBeanList.class };

	public UserBeanContextResolver() throws Exception {
		Map props = new HashMap<String, Object>();
		props.put(JSONJAXBContext.JSON_NOTATION, "MAPPED");
		props.put(JSONJAXBContext.JSON_ROOT_UNWRAPPING, Boolean.FALSE);
		props.put(JSONJAXBContext.JSON_ARRAYS, "[\"user\"]");
		this.context = new JSONJAXBContext(types, props);
	}

	public JAXBContext getContext(Class<?> objectType) {
		for (Class clazz : types) {
			if (clazz.equals(objectType))
				return context;
		}
		return null;
	}
}