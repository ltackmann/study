package org.randompage.samples.jpa.test.utils;

import org.randompage.samples.jpa.test.utils.SpringTester;
import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.sessions.UnitOfWork;
import org.eclipse.persistence.internal.sessions.UnitOfWorkImpl;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * User: "Lars Tackmann"
 * Date: Dec 16, 2008
 */
public class SetupData {
    private final IDatabaseConnection connection;
    private IDataSet dataSet;

    public SetupData(IDatabaseConnection connection) {
        this.connection = connection;
    }

    public void dumpData() throws SQLException, IOException, DataSetException {
        // full database export
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("data.xml"));
    }

    public static void main(String[] args) throws Exception {
        // get entitymanager factory
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
        // ensure factory get closed
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (entityManagerFactory != null)
                    entityManagerFactory.close();
            }
        });
        // get database connection
        UnitOfWorkImpl uow = (UnitOfWorkImpl) JpaHelper.getServerSession(entityManagerFactory).acquireUnitOfWork();
        Connection connection = uow.getAccessor().getConnection();
        // create DBUnit connection
        SetupData data = new SetupData(new DatabaseConnection(connection));
        data.dumpData();
    }

}
