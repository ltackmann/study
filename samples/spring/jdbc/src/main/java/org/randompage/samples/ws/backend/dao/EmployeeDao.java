package org.randompage.samples.ws.backend.dao;

import org.randompage.samples.ws.backend.domain.Employee;

/**
 * @author Lars Tackmann
 */
public interface EmployeeDao extends GenericDao<Employee, Long>{
	Employee find(String username);
}
