package jpa.spring.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableIterator;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import jpa.domain.Address;
import jpa.domain.Country;
import jpa.domain.Customer;
import jpa.spring.config.PersistenceTestJPAConfig;

import static jpa.spring.utils.DbUtils.*;

/**
 * Creates data, asserts it exists and exports it to a file
 */
public class DataExporter extends SpringInjector {
	@PersistenceContext
	private EntityManager entityManager;
	
	public DataExporter() {
		super(PersistenceTestJPAConfig.class);
	}
	
	public void assertEmptyDatabase() {
		int tableCount = doInTransaction(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus status) {
				int tables = 0;
				IDataSet dataSet = getSortedDataSet(entityManager);
				try {
					ITableIterator iterator = dataSet.iterator();
					while (iterator.next()) {
						ITable table = iterator.getTable();
						assertThat(table.getRowCount(), is(0));
						tables++;
					};
				} catch (DataSetException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				return tables;
			}
		});
		
		assertThat(tableCount, is(15));
	}

	public void createTestData() {
		Country country = new Country("USA", "US");
		Address address = new Address("Quack Street", "1112", "Duckburg", country);
		Customer customer = new Customer("donald@duck.com", "donald", "secret", "Donald Duck", address);

		doInTransaction(new TransactionCallback<Object>() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				entityManager.persist(customer);
				return customer;
			}
		});
	}

	public void exportDatabase() {
		doInTransaction(new TransactionCallback<Object>() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				exportData("database-data.xml", entityManager);
				return null;
			}
		});
	}
	
	public static void main(String[] args) {
		DataExporter exporter = new DataExporter();
		exporter.assertEmptyDatabase();
		exporter.createTestData();
		exporter.exportDatabase();
	}
}
