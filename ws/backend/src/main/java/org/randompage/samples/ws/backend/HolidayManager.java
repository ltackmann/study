package org.randompage.samples.ws.backend;

import java.util.Date;
import java.util.Set;

import org.randompage.samples.backend.domain.Employee;
import org.randompage.samples.backend.domain.Holiday;

/**
 * @author Lars Tackmann
 */
public interface HolidayManager {
	/**
	 * Register holiday
	 * 
	 * @param employee
	 * @param from
	 *            Start date of the holiday
	 * @param to
	 *            End date of the holiday
	 * @return List of registered holidays
	 */
	Set<Holiday> registerHoliday(String username, Date from, Date to);

	/**
	 * Return true if employee has holiday on given date
	 * 
	 * @param employee
	 *            Employee to check
	 * @param date
	 *            Date to check
	 * @return
	 */
	boolean hasHoliday(Employee employee, Date date);
}
