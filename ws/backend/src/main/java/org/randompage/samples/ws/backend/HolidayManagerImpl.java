package org.randompage.samples.ws.backend;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.randompage.samples.backend.domain.Employee;
import org.randompage.samples.backend.domain.Holiday;
import org.springframework.stereotype.Repository;

/**
 * @author Lars Tackmann
 */
@Repository
public class HolidayManagerImpl implements HolidayManager {
	private EmployeeManager employeeManager;
	private static Map<Employee, Set<Holiday>> holidays = new HashMap<Employee, Set<Holiday>>();

	public Set<Holiday> registerHoliday(String username, Date from, Date to) {
		Employee employee = employeeManager.getEmployee(username);
		if (employee == null) {
			throw new IllegalStateException(String.format(
					"Employee with name %s does not exists", username));
		}
		// FIXME user another exception
		if (from.after(to)) {
			throw new IllegalStateException(String.format(
					"Start date %s must be before end date %s ", from, to));
		}
		// register holiday
		Set<Holiday> dates = holidays.get(employee);
		if (dates == null) {
			dates = new HashSet<Holiday>();
		}
		dates.add(new Holiday(from, to));
		holidays.put(employee, dates);

		return dates;
	}

	public boolean hasHoliday(Employee employee, Date date) {
		Set<Holiday> dates = holidays.get(employee);
		if (dates == null)
			return false;
		for (Holiday holiday : dates) {
			if (date.after(holiday.getFrom()) && date.before(holiday.getTo()))
				return true;
		}
		return false;
	}

	@Resource
	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}
}
