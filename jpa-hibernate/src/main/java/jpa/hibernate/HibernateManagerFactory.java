package jpa.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import jpa.hibernate.utils.ClassFinder;
import jpa.hibernate.utils.DatabaseProduct;

/**
 * Factory for creating {@link HibernateManager}'s
 */
public class HibernateManagerFactory {
	static private final Map<String, HibernateManager> hibernateManagers = new HashMap<String, HibernateManager>();
	
	/**
	 * Create {@link HibernateManager} for entities listed in rootPackage
	 * 
	 * @param persistenceUnitName
	 * @param databaseProduct
	 * @param connectionUrl
	 * @param rootPackage
	 * @return
	 */
	public static synchronized HibernateManager getHibernateManager(String persistenceUnitName, DatabaseProduct databaseProduct, String connectionUrl, String rootPackage, Properties properties) {
		if(!hibernateManagers.containsKey(persistenceUnitName)) {	
			List<Class<?>> annotatedClasses = ClassFinder.findEntities(rootPackage);
			HibernateManager hibernateManager = new HibernateManager(persistenceUnitName, databaseProduct, connectionUrl, annotatedClasses, properties);
			hibernateManagers.put(persistenceUnitName, hibernateManager);
		}
		return hibernateManagers.get(persistenceUnitName);
	}
	
	/**
	 * Create {@link HibernateManager} for the entities listed in persistence.xml
	 * 
	 * @param persistenceUnitName
	 * @param databaseProduct
	 * @param connectionUrl
	 * @param annotatedClasses
	 * @return
	 */
	public static synchronized HibernateManager getHibernateManager(String persistenceUnitName, DatabaseProduct databaseProduct, String connectionUrl, Properties properties) {
		if(!hibernateManagers.containsKey(persistenceUnitName)) {
			HibernateManager hibernateManager = new HibernateManager(persistenceUnitName, databaseProduct, connectionUrl, properties);
			hibernateManagers.put(persistenceUnitName, hibernateManager);
		}
		return hibernateManagers.get(persistenceUnitName);
	}
	
	/**
	 * Create test {@link HibernateManager} that automatically creates databases and logs SQL, for the entities listed in rootPackage
	 * 
	 * @param persistenceUnitName
	 * @param databaseProduct
	 * @param connectionUrl
	 * @param annotatedClasses
	 * @return
	 */
	public static synchronized HibernateManager getTestHibernateManager(String persistenceUnitName, DatabaseProduct databaseProduct, String connectionUrl, String rootPackage) {
		if(!hibernateManagers.containsKey(persistenceUnitName)) {
			List<Class<?>> annotatedClasses = ClassFinder.findEntities(rootPackage);
			HibernateManager hibernateManager = new HibernateManager(persistenceUnitName, databaseProduct, connectionUrl, annotatedClasses, getTestProperties());
			hibernateManagers.put(persistenceUnitName, hibernateManager);
		} 
		return hibernateManagers.get(persistenceUnitName);
	}
	
	/**
	 * Create test {@link HibernateManager} that automatically creates databases and logs SQL, for the entities listed in persistence.xml
	 * 
	 * @param persistenceUnitName
	 * @param databaseProduct
	 * @param connectionUrl
	 * @return
	 */
	public static synchronized HibernateManager getTestHibernateManager(String persistenceUnitName, DatabaseProduct databaseProduct, String connectionUrl) {
		if(!hibernateManagers.containsKey(persistenceUnitName)) {	
			HibernateManager hibernateManager = new HibernateManager(persistenceUnitName, databaseProduct, connectionUrl, getTestProperties());
			hibernateManagers.put(persistenceUnitName, hibernateManager);
		} 
		return hibernateManagers.get(persistenceUnitName);
	}
	
	private static Properties getTestProperties() {
		Properties properties = System.getProperties();
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.use_sql_comments", "true");
		return properties;
	}
}
