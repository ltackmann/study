package org.randompage.samples.spring.security.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.randompage.samples.spring.security.annotations.Log;
import org.randompage.samples.spring.security.test.utils.SpringTester;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

/**
 * Test spring injection of loggers
 * 
 * @author Lars Tackmann
 * 
 */
public class LogTest extends SpringTester {
	@Autowired
	private Loggable loggable;
	@Log
	private Logger myLogger;

	@Test(description = "should assert that logger injection work")
	public void loggerInjectionTest() {
		// test that auto wiring loggers in spring beans works
		assertThat(loggable, notNullValue());
		assertThat(myLogger, notNullValue());
		// test that logger class detection works
		assertThat(myLogger.getName(), not(equalTo(loggable.getName())));
	}

	static interface Loggable {
		String getName();
	}

	@Service
	static class LoggableBean implements Loggable {
		@Log
		private Logger logger;

		public String getName() {
			logger.info("success");
			return logger.getName();
		}
	}
}
