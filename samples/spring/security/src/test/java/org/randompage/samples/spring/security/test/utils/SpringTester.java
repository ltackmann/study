package org.randompage.samples.spring.security.test.utils;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class SpringTester extends AbstractTestNGSpringContextTests {

}
