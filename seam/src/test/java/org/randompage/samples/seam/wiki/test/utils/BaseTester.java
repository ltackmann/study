package org.randompage.samples.seam.wiki.test.utils;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

/**
 * Readies test environment
 * 
 * @author Lars Tackmann
 */
abstract class BaseTester {
	protected BaseTester() {
		// setup JNDI
		final SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
		final DataSource dataSource = new DriverManagerDataSource(
				"org.apache.derby.jdbc.ClientDriver",
				"jdbc:derby://localhost:1527/wikidb;create=true", "admin",
				"secret");
		builder.bind("java:comp/env/jdbc/wikidb", dataSource);
		try {
			builder.activate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
