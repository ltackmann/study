package jpa.hibernate.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Interceptor;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.jpa.boot.internal.SettingsImpl;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wrap Hibernate and JPA setup classes for optimal use in {@link HibernateManager}
 */
public class HibernateSetup {
	static private final Logger log = LoggerFactory.getLogger(HibernateSetup.class);

	private final TransactionManagerSetup transactionManagerSetup;
	private final EntityManagerFactory entityManagerFactory;
	private final SchemaExport schemaExport;
	
	/**
	 * Construct HibernateSetup from persistence.xml using {@link Persistence#createEntityManagerFactory(String)}
	 * 
	 * @param transactionManagerSetup
	 * @param persistenceUnit
	 */
	public HibernateSetup(TransactionManagerSetup transactionManagerSetup, Properties properties, String persistenceUnit) {
		Properties mergedProperties = mergeProperties(properties, getDefaultHibernateProperties());
		this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit, mergedProperties);
		this.transactionManagerSetup = transactionManagerSetup;
		// unwrap entityManagerFactory to access schemaExport
		SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
		ServiceRegistry serviceRegistry = sessionFactory.getSessionFactoryOptions().getServiceRegistry();
		MetadataImplementor metadata = MetadataProvider.getMetadata();
		this.schemaExport = new SchemaExport(serviceRegistry, metadata, false);
	}
	
	public HibernateSetup(TransactionManagerSetup transactionManagerSetup, Properties properties, List<Class<?>> annotatedClasses) {
		this(transactionManagerSetup, properties, annotatedClasses, new LinkedList<Interceptor>(), new LinkedList<SessionFactoryObserver>());
	}

	public HibernateSetup(TransactionManagerSetup transactionManagerSetup, Properties properties, List<Class<?>> annotatedClasses, List<Interceptor> inteceptors, List<SessionFactoryObserver> observers) {
		Properties mergedProperties = mergeProperties(properties, getDefaultHibernateProperties());

		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		serviceRegistryBuilder.applySettings(mergedProperties);
		serviceRegistryBuilder.applySetting(Environment.DATASOURCE, transactionManagerSetup.getDataSource());
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();

		MetadataSources metadataSources = new MetadataSources(serviceRegistry);
		for (Class<?> annotatedClass : annotatedClasses) {
			metadataSources.addAnnotatedClass(annotatedClass);
		}
		MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
		metadataBuilder.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE);
		Metadata metadata = metadataBuilder.build();

		SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();
		if (!inteceptors.isEmpty()) {
			for (Interceptor interceptor : inteceptors) {
				// intercept all persistence life cycle events for entities
				sessionFactoryBuilder.applyInterceptor(interceptor);
			}
		}

		// listen to when sessionFactories are created and closed
		if (!observers.isEmpty()) {
			sessionFactoryBuilder.addSessionFactoryObservers((SessionFactoryObserver[]) observers.toArray());
		}

		SessionFactory sessionFactory = sessionFactoryBuilder.build();

		SettingsImpl settings = new SettingsImpl();
		settings.setTransactionType(transactionManagerSetup.getTransactionType());

		Map<String, String> configuration = new HashMap<>();
		configuration.put("hibernate.ejb.metamodel.population", "enabled");

		final String persistenceUnitName = transactionManagerSetup.getDataSourceUniqueName();
		this.entityManagerFactory = new EntityManagerFactoryImpl(persistenceUnitName, (SessionFactoryImplementor) sessionFactory, (MetadataImplementor) metadata, settings, configuration);
		this.schemaExport = new SchemaExport(serviceRegistry, (MetadataImplementor) metadata, false);
		this.transactionManagerSetup = transactionManagerSetup;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public SchemaExport getSchemaExport() {
		return schemaExport;
	}

	public TransactionManagerSetup getTransactionManagerSetup() {
		return transactionManagerSetup;
	}

	/**
	 * Get default hibernate properties
	 * 
	 * @return
	 */
	protected Properties getDefaultHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.archive.autodetection", "none");
		properties.put("hibernate.show_sql", "false");
		properties.put("hibernate.format_sql", "false");
		properties.put("hibernate.use_outer_join", "true");
		properties.put("hibernate.default_entity_mode", "pojo");
		properties.put("hibernate.generate_statistics", "false");
		properties.put("hibernate.cache.use_structured_entries", "true");
		properties.put("hibernate.use_identifer_rollback", "true");
		properties.put("hibernate.use_sql_comments", "false");
		properties.put("hibernate.order_updates", "true");
		properties.put("hibernate.order_inserts", "true");
		properties.put("hibernate.default_batch_fetch_size", "8");
		properties.put("hibernate.max_fetch_depth", "3");
		properties.put("hibernate.jdbc.fetch_size", "8");
		properties.put("hibernate.jdbc.batch_size", "100");
		properties.put("hibernate.jdbc.batch_versioned_data", "false");
		properties.put("hibernate.jdbc.use_scrollable_resultset", "true");

		// <!-- Cache --, "
		properties.put("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
		properties.put("hibernate.cache.use_minimal_puts", "false"); // set true if using clustered cache
		properties.put("hibernate.cache.use_query_cache", "false");
		properties.put("hibernate.cache.use_second_level_cache", "false");

		// <!-- Connection/transaction --, "
		properties.put("hibernate.connection.release_mode", "auto");
		properties.put("hibernate.connection.autocommit", "false");
		properties.put("hibernate.transaction.flush_before_completion", "true");
		properties.put("hibernate.transaction.auto_close_session", "false");
		properties.put("hibernate.bytecode.use_reflection_optimizer", "true");
		properties.put("hibernate.transaction.jta.platform", "org.hibernate.service.jta.platform.internal.BitronixJtaPlatform");

		// TODO implement own
		properties.put("hibernate.insertsorter.class", "com.schantz.foundation.hibernate.InsertSorter");
		
		return mergeProperties(getDebugHibernateProperties(), properties);
	}
	
	protected Properties getDebugHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.hbm2ddl.auto", "create-drop");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.use_sql_comments", "true");	
		return properties;
	}

	/**
	 * Merge original properties with values from property overrides 
	 * 
	 * @param propertiesOverrides
	 * @param originalProperties
	 * @return
	 */
	protected Properties mergeProperties(Properties propertiesOverrides, Properties originalProperties) {
		Properties merged = new Properties();
		merged.putAll(originalProperties);

		for(Entry<Object, Object> e : propertiesOverrides.entrySet()) {
			 if (merged.get(e.getKey()) != null) {
				 log.warn("Property " + e.getKey() + " was already set to: " + merged.get(e.getKey()) + " overriding with " + e.getValue());
			 }
			 merged.put(e.getKey(), e.getValue());
        }
		return merged;
	}
}