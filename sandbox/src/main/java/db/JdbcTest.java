package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class JdbcTest {
	@Test
	public void runTests() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver");
		
        String user = "sa";
        String pwd = "";
        String url = "jdbc:hsqldb:file:~/Projects/java/sandbox/target/hsql.db";
        Connection connection = DriverManager.getConnection (url, user,pwd);
        
        createDatabase(connection);
        insertData(connection);
        queryData(connection);
        
        connection.close();
	}
	
	private void createDatabase(Connection connection) throws SQLException {
		String sqlCreate = 
				"DROP TABLE IF EXISTS employee;" + 
				"CREATE TABLE employee (" +
					"firstname varchar(32)," +
					"lastname varchar(32)" +
				");"; 
		
		try (Statement statement = connection.createStatement()) {
			statement.execute(sqlCreate);
			// TODO test command went well
		}
	}
	
	private void insertData(Connection connection) throws SQLException {
		String sqlInsert = 
				"INSERT INTO " +
						"employee " +
				"VALUES " +
						"('Lars', 'Tackmann')";
		
		try (Statement statement = connection.createStatement()) {
			statement.execute(sqlInsert);
			// TODO test command went well
		}
	}
	
	public void queryData(Connection connection) throws SQLException {
		String sqlQuery = 
				"SELECT " +
						"firstname, lastname " + 
				"FROM " + 
						"employee";
		try (Statement statement = connection.createStatement()) {
			ResultSet result = statement.executeQuery(sqlQuery);
			while(result.next()) {
				String firstName = result.getString(1);
				String lastName = result.getString(2);
				System.out.println("Name is: " + firstName + " " + lastName);
			}
		}
	}
}
