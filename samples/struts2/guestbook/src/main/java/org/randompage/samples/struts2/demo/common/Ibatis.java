package org.randompage.samples.struts2.demo.common;

import java.io.IOException;
import java.io.Reader;

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class Ibatis {
	private static Logger log = Logger.getLogger(Ibatis.class);
	private static SqlMapClient sqlMap = null;
	private static String resource = "sqlMap-config.xml";

	public static synchronized SqlMapClient getSqlMap() throws IOException {
		if (sqlMap == null) {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			log.debug("Ibatis initialized");
		}
		return sqlMap;
	}

}