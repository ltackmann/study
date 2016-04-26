package jpa.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import jpa.hibernate.utils.ClassFinder;
import jpa.hibernate.utils.DatabaseProduct;

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
	public static synchronized HibernateManager getHibernateManager(String persistenceUnitName, DatabaseProduct databaseProduct, String connectionUrl, String rootPackage) {
		if(!hibernateManagers.containsKey(persistenceUnitName)) {
			Properties properties = System.getProperties();		
			List<Class<?>> annotatedClasses = ClassFinder.findEntities(rootPackage);
			HibernateManager hibernateManager = new HibernateManager(persistenceUnitName, databaseProduct, connectionUrl, annotatedClasses, properties);
			hibernateManagers.put(persistenceUnitName, hibernateManager);
		}
		return hibernateManagers.get(persistenceUnitName);
	}
	
	/**
	 * Create {@link HibernateManager} for listed entities 
	 * 
	 * @param persistenceUnitName
	 * @param databaseProduct
	 * @param connectionUrl
	 * @param annotatedClasses
	 * @return
	 */
	public static synchronized HibernateManager getHibernateManager(String persistenceUnitName, DatabaseProduct databaseProduct, String connectionUrl, List<Class<?>> annotatedClasses) {
		if(!hibernateManagers.containsKey(persistenceUnitName)) {
			Properties properties = System.getProperties();
			HibernateManager hibernateManager = new HibernateManager(persistenceUnitName, databaseProduct, connectionUrl, annotatedClasses, properties);
			hibernateManagers.put(persistenceUnitName, hibernateManager);
		}
		return hibernateManagers.get(persistenceUnitName);
	}
	
	/**
	 * Create {@link HibernateManager} for entities listed in persistence.xml
	 * 
	 * @param persistenceUnitName
	 * @param databaseProduct
	 * @param connectionUrl
	 * @param annotatedClasses
	 * @return
	 */
	public static synchronized HibernateManager getHibernateManager(String persistenceUnitName, DatabaseProduct databaseProduct, String connectionUrl) {
		if(!hibernateManagers.containsKey(persistenceUnitName)) {
			Properties properties = System.getProperties();
			HibernateManager hibernateManager = new HibernateManager(persistenceUnitName, databaseProduct, connectionUrl, properties);
			hibernateManagers.put(persistenceUnitName, hibernateManager);
		}
		return hibernateManagers.get(persistenceUnitName);
	}
}
