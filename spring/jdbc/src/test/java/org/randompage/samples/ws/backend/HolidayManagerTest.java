package org.randompage.samples.ws.backend;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;

/**
 * @author Lars Tackmann
 */

public class HolidayManagerTest {
	public static final String username = "lamstrong";

	/**
	 * Mock that no user exists and assert that registering holiday fails 
	 */
	@Test(expected = IllegalStateException.class)
	public void noUserForHolidayTest() {
		// mocking and stubbing
		EmployeeManager employeeManager = mock(EmployeeManager.class);
		when(employeeManager.getEmployee(username)).thenReturn(null);
		// subject under test
		HolidayManagerImpl holidayManager = new HolidayManagerImpl();
		holidayManager.setEmployeeManager(employeeManager);
		// test
		holidayManager.registerHoliday(username, new Date(), new Date());
	}
	
	@Test
	public void orderHolidayTest() {
		//TODO implement test
		assert 1 == 1;
	}
}
