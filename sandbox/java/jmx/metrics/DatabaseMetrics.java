package jmx.metrics;

import javax.management.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseMetrics implements DynamicMBean {
    private static Map<String, Object> stats;

    @Override
    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        return getStats().get(attribute);
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        getStats().put(attribute.getName(), attribute.getValue());
    }

    @Override
    public AttributeList getAttributes(String[] attributes) {
        AttributeList list = new AttributeList();
        for (Object name : getStats().keySet()) {
            list.add(new Attribute((String) name, getStats().get(name)));
        }
        return list;
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        return null;
    }

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        return null;
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        ArrayList<MBeanAttributeInfo> attrs = new ArrayList();
        for (Object name : getStats().keySet()) {
            MBeanAttributeInfo attr = new MBeanAttributeInfo(
                    (String) name,
                    "java.lang.String",
                    "Property " + name,
                    true,
                    false,
                    false
            );
            attrs.add(attr);
        }
        MBeanInfo info = new MBeanInfo(
                this.getClass().getName(),
                "App MXBean",
                (MBeanAttributeInfo[]) attrs.toArray(new MBeanAttributeInfo[0]),
                null,
                null,
                null
        );
        return info;
    }

    public static void setLongValue(String name, Long value) {
        getStats().put(name, value);
    }

    private static Map<String,Object> getStats() {
        if (stats == null) {
            stats = new HashMap<>();
            stats.put("Account", 123L);
            stats.put("Address", 12L);
            stats.put("Company", 13L);
            stats.put("Person", 23L);
        }
        return stats;
    }
}