package spring.jdbc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Lars Tackmann
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class SpringTest {
	private static final String username = "ltackmann";
	@Resource
	private EmployeeManager employeeManager;
	@Resource
	private HolidayManager holidayManager;

	/**
	 * Integration test that asserts that spring injection is working as
	 * expected
	 */
	@Test
	public void springIntegrationTest() {
		// tests injection in test class
		assertThat(employeeManager, notNullValue());
		assertThat(holidayManager, notNullValue());
		// test class that is itself auto wired
		Date from = daysFromNow(1);
		Date to = daysFromNow(3);
		assertThat(holidayManager.registerHoliday(username, from, to),
				notNullValue());
		// test that holiday schedule is updated as desired
		Employee employee = employeeManager.getEmployee(username);
		assertThat(employee, notNullValue());
		assertThat(holidayManager.hasHoliday(employee, daysFromNow(2)),
				is(true));
	}

	private Date daysFromNow(int days) {
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.DAY_OF_MONTH, days);
		return instance.getTime();
	}
}
