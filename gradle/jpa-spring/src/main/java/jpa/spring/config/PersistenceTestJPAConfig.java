package jpa.spring.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * Test configuration that stores data in memory database
 */
@Configuration
public class PersistenceTestJPAConfig extends PersistenceJPAConfig {
	protected JpaVendorAdapter getVendorAdapter() {
		return new HibernateJpaVendorAdapter() {
			{
				setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
				setDatabase(Database.HSQL);
				setGenerateDdl(true);
				setShowSql(true);
			}
		};
	}
	
	protected String getConnectionUrl() {
		return "jdbc:hsqldb:mem:mymemdb";
	}
}
