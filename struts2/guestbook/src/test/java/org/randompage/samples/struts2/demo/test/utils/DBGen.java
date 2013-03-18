package org.randompage.samples.struts2.demo.test.utils;

import java.sql.SQLException;

import org.randompage.samples.struts2.demo.common.Ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Generates database
 * 
 * @author Lars Tackmann
 */
public class DBGen {
	private static SqlMapClient sqlMap;

	public static void main(String[] args) throws Exception {
		sqlMap = Ibatis.getSqlMap();
		oracleSetUp();
	}

	/*
	 * Generate PostgreSQL database
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static void postgresSetUp() throws Exception {
		try {
			sqlMap.update("createSchema", null);
		} catch (SQLException e1) {
			// schema already exists so recreate it
			execute("dropSchema", "createSchema");
		}
		// create DB objects
		execute("createTable1", "createTable2", "createFunction1");
	}

	/*
	 * Generate Oracle database
	 */
	private static void oracleSetUp() throws Exception {
		// drop any existing objects
		try {
			sqlMap.update("dropObjects", null);
		} catch (SQLException e) {
			// fall through
		}
		// create DB objects
		execute("createTable1", "createTable2", "createFunction1",
				"createSequences");
	}

	private static void execute(String... sqls) throws SQLException {
		for (String sql : sqls)
			sqlMap.update(sql);
	}
}
