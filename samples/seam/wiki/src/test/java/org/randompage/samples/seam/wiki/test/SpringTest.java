package org.randompage.samples.seam.wiki.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.randompage.samples.seam.wiki.test.utils.NotNullOrEmpty.notNullOrEmpty;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.randompage.samples.seam.wiki.client.SystemManager;
import org.randompage.samples.seam.wiki.impl.TracerInterceptor;
import org.randompage.samples.seam.wiki.test.utils.SpringTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * Test Spring injection and aspect oriented programming
 * 
 * @author Lars Tackmann
 */
public class SpringTest extends SpringTester {
	@Autowired
	private SystemManager systemManager;

	@Resource
	private DataSource dataSource;

	@Test(description = "should get injected bean instance")
	public void autowireTest() {
		assertThat(systemManager, notNullValue());
	}

	@Test(description = "should get injected JSR 250 resources", dependsOnMethods = "lifecycleTest")
	public void resourceTest() {
		assertThat(dataSource, notNullValue());
		assertThat(systemManager.getApplicationName(), notNullOrEmpty());
	}

	@Test(description = "should retrieve value created by JSR 250 lifecycle", dependsOnMethods = "autowireTest")
	public void lifecycleTest() {
		assertThat(systemManager.getDatabaseStatistics(), notNullOrEmpty());
	}

	@Test(description = "should retrieve incremented invocation count", dependsOnMethods = "resourceTest")
	public void interceptorTest() {
		// ensure that calls to getApplicationName() are traced (from resourceTest)
		assertThat(TracerInterceptor.getInvocationCount(), greaterThan(0));
	}
}
