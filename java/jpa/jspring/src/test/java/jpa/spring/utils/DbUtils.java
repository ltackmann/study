package jpa.spring.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;

public class DbUtils { 
	/**
	 * Export data in database backed by entityManager to dataSetFile
	 * @param dataSetFile
	 * @param entityManager
	 */
	public static void exportData(String dataSetFile, EntityManager entityManager) {
		IDataSet dataset = getSortedDataSet(entityManager);
    	try {
            FlatXmlDataSet.write(dataset, new FileOutputStream(dataSetFile));
		} catch (DatabaseUnitException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 	
    }
    
	 /**
	  * Get a data set sorted by foreign keys to avoid foreign key constraints violations
	  * 
	  * @param em
	  * @return
	  */
    public static IDataSet getSortedDataSet(EntityManager em) {
    	Session session = em.unwrap(Session.class);
    	return session.doReturningWork(new ReturningWork<IDataSet>() {
			@Override
			public IDataSet execute(Connection connection) throws SQLException {
				return getSortedDataSet(connection);
			}
		});
    } 
    
    // Get a data set sorted by foreign keys to avoid foreign key constraints violations
    private static IDataSet getSortedDataSet(Connection jdbcConnection) {
    	IDataSet dataset = null;
    	try {
    		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
    		ITableFilter filter = new DatabaseSequenceFilter(connection); 
    		dataset =  new FilteredDataSet(filter, connection.createDataSet());
    	} catch (DatabaseUnitException | SQLException e) {
    		e.printStackTrace();
			throw new RuntimeException(e);
    	}
    	return dataset;
    }
    
    public static void importData(String filename, EntityManager em) {
    	Session session = em.unwrap(Session.class);
		session.doWork(new Work() {
		    @Override
		    public void execute(Connection connection) throws SQLException {
		    	importData(filename, connection);
		    }
		});       	
    }
    
    private static void importData(String dataSetFile, Connection jdbcConnection) {
    	try {
    		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
			FlatXmlDataSet dataSet = new FlatXmlDataSetBuilder().build(new File(dataSetFile));	
			DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
		} catch (MalformedURLException | DatabaseUnitException | SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
}
