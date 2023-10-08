package org.randompage.bookmarking.backend.testUtils;

import org.mockito.Mockito;
import org.springframework.beans.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory bean for easy use of mocks in when testing auto-wired code
 *
 * @author Lars Tackmann
 */
public class MocksFactory implements FactoryBean {
    private static Map<Class,Object> typeMap = new HashMap<Class,Object>();

    private Class type;// the created object type

    public void setType(final Class type) {
        this.type = type;
    }

    @Override
    public Object getObject() throws Exception {
        if(!typeMap.containsKey(type)) {
            typeMap.put(type,Mockito.mock(type));
        }

        return typeMap.get(type);
    }

    @Override
    public Class getObjectType() {
        return type;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
