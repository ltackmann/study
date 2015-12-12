package jpa.test.utils;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import jpa.config.PersistenceJPAConfig;

public abstract class SpringTester {
    public SpringTester() {
        // Start Spring
        final AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(PersistenceJPAConfig.class);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (ctx != null)
                	ctx.close();
            }
        });
        // Inject spring beans
        ctx.getAutowireCapableBeanFactory().autowireBean(this);
    }
}
