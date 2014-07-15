package org.randompage.samples.seam.wiki.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.randompage.samples.seam.wiki.client.SystemManager;
import org.randompage.samples.seam.wiki.client.Tracer;
import org.springframework.stereotype.Component;

@Component
public class SystemManagerBean implements SystemManager {
	private String statistics;
	@Resource(name = "appName")
	private String applicationName;
	@PersistenceContext(unitName = "wiki")
	private EntityManager entityManager;

	@PostConstruct
	protected void postConstruct() {
		Session session = (Session) entityManager.getDelegate();
		SessionFactory factory = session.getSessionFactory();
		statistics = factory.getStatistics().toString();
	}

	@Override
	public String getDatabaseStatistics() {
		return statistics;
	}

	@Tracer
	@Override
	public String getApplicationName() {
		return applicationName;
	}
}
