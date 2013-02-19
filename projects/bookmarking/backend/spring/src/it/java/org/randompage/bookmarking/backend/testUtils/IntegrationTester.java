package org.randompage.bookmarking.backend.testUtils;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import javax.sql.DataSource;
import java.net.URL;

/**
 * Readies test environment for integration tests
 *
 * @author Lars Tackmann
 */
public abstract class IntegrationTester {
    @Autowired
    private DataSource dataSource;

    public IntegrationTester() {
        // H2 data source
        final DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:h2:~/.h2/devdb;FILE_LOCK=NO");
        dataSource.setDriverClassName("org.h2.Driver");

        // bind to JNDI
        final SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind("java:comp/env/jdbc/devdb", dataSource);
        try {
            builder.activate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setUpDBUnit(String dataSetLocation) throws Exception {
        URL input = Thread.currentThread().getContextClassLoader().getResource(dataSetLocation);
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(input);
        IDatabaseConnection dbConn = new DatabaseDataSourceConnection(dataSource);
        DatabaseOperation.CLEAN_INSERT.execute(dbConn, dataSet);
    }
}
