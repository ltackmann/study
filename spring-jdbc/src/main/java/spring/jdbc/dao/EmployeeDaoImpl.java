package spring.jdbc.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import spring.jdbc.domain.Employee;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Lars Tackmann
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	private DataSource dataSource;

	@Resource
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Employee find(String username) {
		SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(dataSource);
		// SQL select
		Employee employee = jdbcTemplate.queryForObject(
				"select * from employees where username = ?",
				ParameterizedBeanPropertyRowMapper.newInstance(Employee.class),
				username);
		return employee;
	}

	public Long create(Employee employee) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("employees").usingGeneratedKeyColumns("id");
		// SQL insert
		Number newId = jdbcInsert
				.executeAndReturnKey(new BeanPropertySqlParameterSource(
						employee));
		return newId.longValue();
	}

	public void delete(Employee employee) {
		SimpleJdbcTemplate jdbcTemplate = new SimpleJdbcTemplate(dataSource);
		// SQL delete
		jdbcTemplate.update("delete from employees where id = ?", employee
				.getId());
	}

	public Employee find(Long id) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
				.withFunctionName("employeeFindById").returningResultSet(
						"rs",
						ParameterizedBeanPropertyRowMapper
								.newInstance(Employee.class));
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("employee_id", id);
		// TODO use Function that takes primitive and returns a cursor
		return jdbcCall.executeFunction(Employee.class, args);
	}

	public void update(Employee employee) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
				.withProcedureName("employeeUpdate").declareParameters(
						new SqlParameter("in_id", Types.REF),
						new SqlOutParameter("updated", Types.BOOLEAN));

		// jdbcCall.e
		// TODO Use SP that takes an cursor and returns a primitive
	}
}
