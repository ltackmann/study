package jpa.hibernate.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bitronix.tm.TransactionManagerServices;
import bitronix.tm.resource.jdbc.PoolingDataSource;

/**
 * Provides a database connection pool with the Bitronix JTA transaction manager
 * (http://docs.codehaus.org/display/BTM/Home).
 * <p>
 * Hibernate will look up the datasource and <code>UserTransaction</code>
 * through JNDI, that's why you also need a <code>jndi.properties</code> file. A
 * minimal JNDI context is bundled with and started by Bitronix.
 * </p>
 */
public class TransactionManagerSetup {
	private static final Logger logger = LoggerFactory.getLogger(TransactionManagerSetup.class.getName());

	protected final Context context = new InitialContext();
	protected final PoolingDataSource datasource;
	protected final String dataSourceUniqueName;

	public TransactionManagerSetup(DatabaseProduct databaseProduct, String connectionURL, String dataSourceUniqueName)
			throws Exception {
		this.dataSourceUniqueName = dataSourceUniqueName;
		logger.debug("Starting database connection pool with id " + dataSourceUniqueName);

		logger.debug("Setting stable unique identifier for transaction recovery");
		TransactionManagerServices.getConfiguration().setServerId("myServer1234");

		logger.debug("Disabling JMX binding of manager in unit tests");
		TransactionManagerServices.getConfiguration().setDisableJmx(true);

		logger.debug("Disabling transaction logging for unit tests");
		TransactionManagerServices.getConfiguration().setJournal("null");

		logger.debug("Disabling warnings when the database isn't accessed in a transaction");
		TransactionManagerServices.getConfiguration().setWarnAboutZeroResourceTransaction(false);

		logger.debug("Creating connection pool");
		datasource = new PoolingDataSource();
		datasource.setUniqueName(dataSourceUniqueName);
		datasource.setMinPoolSize(1);
		datasource.setMaxPoolSize(5);
		datasource.setPreparedStatementCacheSize(10);

		// Our locking/versioning tests assume READ COMMITTED transaction
		// isolation. This is not the default on MySQL InnoDB, so we set
		// it here explicitly.
		datasource.setIsolationLevel("READ_COMMITTED");

		// Hibernate's SQL schema generator calls connection.setAutoCommit(true)
		// and we use auto-commit mode when the EntityManager is in suspended
		// mode and not joined with a transaction.
		datasource.setAllowLocalTransactions(true);

		logger.info("Setting up database connection: " + databaseProduct);
		databaseProduct.configuration.configure(datasource, connectionURL);

		logger.debug("Initializing transaction and resource management");
		datasource.init();
	}

	public Context getNamingContext() {
		return context;
	}

	public UserTransaction getUserTransaction() {
		try {
			return (UserTransaction) getNamingContext().lookup("java:comp/UserTransaction");
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public DataSource getDataSource() {
		try {
			return (DataSource) getNamingContext().lookup(dataSourceUniqueName);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public void rollback() {
		UserTransaction tx = getUserTransaction();
		try {
			if (tx.getStatus() == Status.STATUS_ACTIVE || tx.getStatus() == Status.STATUS_MARKED_ROLLBACK)
				tx.rollback();
		} catch (Exception ex) {
			System.err.println("Rollback of transaction failed, trace follows!");
			ex.printStackTrace(System.err);
		}
	}
	
	public PersistenceUnitTransactionType getTransactionType() {
		return PersistenceUnitTransactionType.JTA;
	}
	
	public String getDataSourceUniqueName() {
		return dataSourceUniqueName;
	}

	public void close() {
		logger.debug("Stopping database connection pool with id " + dataSourceUniqueName);
		datasource.close();
		TransactionManagerServices.getTransactionManager().shutdown();
	}
}