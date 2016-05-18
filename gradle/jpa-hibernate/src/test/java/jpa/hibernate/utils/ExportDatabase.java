package jpa.hibernate.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class ExportDatabase {
    private final IDatabaseConnection connection;
    
    public ExportDatabase(Connection connection) throws DatabaseUnitException {
    	this(new DatabaseConnection(connection));
    }

    public ExportDatabase(IDatabaseConnection connection) {
        this.connection = connection;
    }

    public void dumpData(String file) throws SQLException, IOException, DataSetException {
        // full database export
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream(file));
    }
}
