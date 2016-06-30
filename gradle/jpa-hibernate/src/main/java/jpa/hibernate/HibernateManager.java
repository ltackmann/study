package jpa.hibernate;

import jpa.hibernate.utils.DatabaseProduct;
import jpa.hibernate.utils.HibernateSetup;
import jpa.hibernate.utils.TransactionManagerSetup;

import javax.persistence.*;
import javax.transaction.UserTransaction;

import org.hibernate.SessionFactory;

import java.util.*;

/**
 * Interface for working with both plain JPA and advanced Hibernate features
 */
public class HibernateManager {
	private final HibernateSetup hibernateSetup;

	/**
	 * Create {@link HibernateManager} from list of annotated classes
	 * 
	 * @param persistenceUnitName
	 * @param databaseProduct
	 * @param connectionUrl
	 * @param annotatedClasses
	 * @param properties
	 */
	protected HibernateManager(String persistenceUnitName, DatabaseProduct databaseProduct, String connectionUrl, List<Class<?>> annotatedClasses, Properties properties) {
		try {
			TransactionManagerSetup transactionManagerSetup = new TransactionManagerSetup(databaseProduct, connectionUrl, persistenceUnitName);
			properties.put("hibernate.dialect", databaseProduct.hibernateDialect);
			this.hibernateSetup = new HibernateSetup(transactionManagerSetup, properties, annotatedClasses);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Create {@link HibernateManager} from classes listed in persistence.xml
	 * 
	 * @param persistenceUnitName
	 * @param databaseProduct
	 * @param connectionUrl
	 * @param properties
	 */
	protected HibernateManager(String persistenceUnitName, DatabaseProduct databaseProduct, String connectionUrl, Properties properties) {
		try {
			TransactionManagerSetup transactionManagerSetup = new TransactionManagerSetup(databaseProduct, connectionUrl, persistenceUnitName);
			properties.put("hibernate.dialect", databaseProduct.hibernateDialect);
			this.hibernateSetup = new HibernateSetup(transactionManagerSetup, properties, persistenceUnitName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Export schema to file
	 * 
	 * @param outputFilename
	 */
	public void createSchema(String outputFilename) {
		hibernateSetup.createSchema(outputFilename);
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return hibernateSetup.getEntityManagerFactory();
	}

	/**
	 * closing the HibernateManager will also close any EntityManager's created from the underlying EntityManagerFactory
	 */
	public void close() {
		EntityManagerFactory entityManagerFactory = getEntityManagerFactory();
		if(entityManagerFactory != null) {
			SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
			sessionFactory.close();
		} else {
			// only close database connection directly if no EntityManagerFactory exists as 
			// closing the EntityManagerFactory will itself close the database connection
			hibernateSetup.getTransactionManagerSetup().close();
		}
	}

	public UserTransaction getTransaction() {
		return hibernateSetup.getTransactionManagerSetup().getUserTransaction();
	}
}
