package org.randompage.samples.struts2.demo.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.sql.SQLException;

import org.randompage.samples.struts2.demo.common.Ibatis;
import org.randompage.samples.struts2.demo.domain.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Test database access
 * 
 * @author Lars Tackmann
 */
public class DBTest {
	private SqlMapClient sqlMap;
	// test data
	private User user;
	private static final String username = "ltackmann";

	@Test(description = "should create a user")
	public void createUser() throws Exception {
		User user = new User();
		user.setFirstName("Lars");
		user.setLastName("Tackmann");
		user.setUsername(username);
		sqlMap.insert("addUser", user);
	}

	@Test(description = "should delete user", dependsOnMethods = { "updateUser" })
	public void deleteUser() throws SQLException {
		sqlMap.delete("deleteUser", user);
		user = (User) sqlMap.queryForObject("getUser", username);
		assertThat(user, nullValue());
	}

	@Test(description = "should retrieve created user", dependsOnMethods = { "createUser" })
	public void retrieveUser() throws SQLException {
		user = (User) sqlMap.queryForObject("getUser", username);
		checkUser(user);
	}

	@BeforeClass
	public void setUp() throws Exception {
		sqlMap = Ibatis.getSqlMap();
		assertThat(sqlMap, notNullValue());
		// clean up old data
		User testUser = null;
		try {
			testUser = (User) sqlMap.queryForObject("getUser", username);
		} catch (Exception e) {
			// nothing
		}
		if (testUser != null)
			sqlMap.delete("deleteUser", testUser);
	}

	@Test(description = "should update user and assert that changes are persisted", dependsOnMethods = { "retrieveUser" })
	public void updateUser() throws SQLException {
		assertThat(user.getFirstName(), not(equalTo(username)));
		user.setFirstName(username);
		sqlMap.update("updateUser", user);
		user = (User) sqlMap.queryForObject("getUser", username);
		checkUser(user);
		assertThat(user.getFirstName(), equalTo(username));
	}
	
	private void checkUser(User user) {
		assertThat(user, notNullValue());
		assertThat(user.getUsername(), equalTo(username));
	}
}
