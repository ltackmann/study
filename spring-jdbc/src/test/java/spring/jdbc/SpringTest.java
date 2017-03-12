package spring.jdbc;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.annotation.Resource;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import spring.jdbc.dao.EmployeeDao;
import spring.jdbc.domain.Employee;

/**
 * @author Lars Tackmann
 */
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class SpringTest extends AbstractTestNGSpringContextTests {
	private static final String username = "ltackmann";
	@Resource
	private EmployeeDao employeeDao;

	/**
	 * Integration test that asserts that spring injection is working as
	 * expected
	 */
	@Test
	public void springInjectionTest() {
		assertThat(employeeDao, notNullValue());
	}
	
	@Test(dependsOnMethods = "springInjectionTest")
	public void noInitialDataShouldExists() {
		Employee employee = employeeDao.find(username);
		assertThat(employee, nullValue());
	}

}
