package jpa.hibernate;

import jpa.hibernate.utils.DatabaseProduct;
import jpa.hibernate.utils.HibernateSetup;
import jpa.hibernate.utils.TransactionManagerSetup;

import javax.persistence.*;
import javax.transaction.UserTransaction;

import org.hibernate.tool.hbm2ddl.SchemaExport;

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
		SchemaExport schemaExport = hibernateSetup.getSchemaExport();
		schemaExport = schemaExport.setOutputFile(outputFilename);
		schemaExport.setFormat(false);
		schemaExport.execute(true, false, false, true);
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return hibernateSetup.getEntityManagerFactory();
	}

	public void close() {
		getEntityManagerFactory().close();
		hibernateSetup.getTransactionManagerSetup().close();
	}

	public UserTransaction getTransaction() {
		return hibernateSetup.getTransactionManagerSetup().getUserTransaction();
	}
}
