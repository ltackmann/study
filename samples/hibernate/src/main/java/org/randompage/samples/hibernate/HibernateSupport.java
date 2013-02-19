package org.randompage.samples.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSupport {
	private static SessionFactory factory;

	public static synchronized Session currentSession() {
		if (factory == null) {
			// read hibernate.cfg
			factory = new Configuration().configure().buildSessionFactory();
			// close database connections prior to shutdown
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					if (factory != null)
						factory.close();
				}
			});
		}
		return factory.getCurrentSession();
	}

	public static void closeSession(Session session) {
		session.close();
	}
}
