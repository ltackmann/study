package org.randompage.samples.jaxrs.spring.test.utils;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
public abstract class SpringTester extends AbstractTestNGSpringContextTests {

}
