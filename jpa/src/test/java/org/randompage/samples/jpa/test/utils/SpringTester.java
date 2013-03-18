package org.randompage.samples.jpa.test.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: "Lars Tackmann"
 * Date: Dec 14, 2008
 */
public abstract class SpringTester {
    public SpringTester() {
        // Start Spring
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        // Inject spring beans
        ctx.getAutowireCapableBeanFactory().autowireBean(this);
    }
}
