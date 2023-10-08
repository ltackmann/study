package jpa.spring.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("jpa.spring.impl")
public class PersistenceJPAConfig {
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(this.createDataSource());
		factoryBean.setPackagesToScan(new String[] { "jpa.domain" });
		factoryBean.setJpaVendorAdapter(getVendorAdapter());
		factoryBean.setJpaProperties(this.additionlProperties());

		return factoryBean;
	}

	@Bean
	public DataSource createDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		dataSource.setUrl(getConnectionUrl());
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	protected Properties additionlProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.format_sql", "true");
		return properties;
	}
	
	protected JpaVendorAdapter getVendorAdapter() {
		return new HibernateJpaVendorAdapter() {
			{
				setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
				setDatabase(Database.HSQL);
				//setGenerateDdl(true);
				setShowSql(true);
			}
		};
	}
	
	protected String getConnectionUrl() {
		// "jdbc:hsqldb:mem:mymemdb"
		return "jdbc:hsqldb:file:~/Projects/java/jpa-spring/hsql.db";
	}
}
