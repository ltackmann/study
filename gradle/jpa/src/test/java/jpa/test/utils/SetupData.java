package jpa.test.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import jpa.config.PersistenceJPAConfig;

public class SetupData {
    private final IDatabaseConnection connection;

    public SetupData(IDatabaseConnection connection) {
        this.connection = connection;
    }

    public void dumpData() throws SQLException, IOException, DataSetException {
        // full database export
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("data.xml"));
    }

    public static void main(String[] args) throws Exception {
        // get entity manager from spring and grab its JDBC connection
    	final AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(PersistenceJPAConfig.class);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (ctx != null)
                	ctx.close();
            }
        });
        final EntityManager entityManager = ctx.getBean(EntityManager.class);
        java.sql.Connection connection = entityManager.unwrap(java.sql.Connection.class);
        
        // export database
        SetupData data = new SetupData(new DatabaseConnection(connection));
        data.dumpData();
    }

}
