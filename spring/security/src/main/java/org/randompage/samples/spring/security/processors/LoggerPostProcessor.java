package org.randompage.samples.spring.security.processors;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.randompage.samples.spring.security.annotations.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class LoggerPostProcessor implements BeanPostProcessor {
    @SuppressWarnings("unchecked")
    private static Map<Class, Logger> loggers = new HashMap<Class,Logger>();

    public Object postProcessAfterInitialization(Object bean, String beanName)
        throws BeansException {
        return bean;
    }

    @SuppressWarnings("unchecked")
    public Object postProcessBeforeInitialization(final Object bean,
        String beanName) throws BeansException {
        Class clazz = bean.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getAnnotation(Log.class) != null) {
                field.setAccessible(true);
                Logger logger = null;
                if (loggers.containsKey(clazz)) {
                    logger = loggers.get(clazz);
                } else {
                    logger = LoggerFactory.getLogger(clazz);
                    loggers.put(clazz, logger);
                }
                try {
                    field.set(bean, logger);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
       }
       return bean;
   }
}
