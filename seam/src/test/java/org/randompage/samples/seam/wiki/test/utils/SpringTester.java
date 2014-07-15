package org.randompage.samples.seam.wiki.test.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Test Spring outside of a container
 * 
 * @author Lars Tackmann
 */
public abstract class SpringTester extends BaseTester {
	protected SpringTester(String[] files) {
		// dependency injection via application context
		ApplicationContext ctx = new ClassPathXmlApplicationContext(files);
		ctx.getAutowireCapableBeanFactory().autowireBean(this);
	}

	protected SpringTester() {
		this(new String[]{"/applicationContext.xml"});
	}
}
