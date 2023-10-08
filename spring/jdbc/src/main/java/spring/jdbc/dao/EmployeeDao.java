package spring.jdbc.dao;

import spring.jdbc.domain.Employee;

/**
 * @author Lars Tackmann
 */
public interface EmployeeDao extends GenericDao<Employee, Long>{
	Employee find(String username);
}
