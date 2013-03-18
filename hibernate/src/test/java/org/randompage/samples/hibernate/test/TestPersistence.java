package org.randompage.samples.hibernate.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.randompage.samples.hibernate.HibernateSupport;
import org.randompage.samples.hibernate.domain.Result;

import junit.framework.TestCase;

public class TestPersistence extends TestCase {
	private Session session;
	private static String msisdn;

	public void setUp() {
		session = HibernateSupport.currentSession();
	}

	public void testHQLQuery() {
		Transaction tx = session.beginTransaction();
		Query query = session.getNamedQuery("result.all");
		List result = query.list();
		tx.rollback();
		// test result
		assertTrue(result.size() > 0);
		Iterator iter = result.iterator();
		while (iter.hasNext()) {
			Result elm = (Result) iter.next();
			String member = elm.getMemberMsisdn();
			if (msisdn == null)
				msisdn = member;
			assertNotNull(member);
			assertTrue(member.length() > 0);
		}
	}

	public void testStoredProcedure() {
		Transaction tx = session.beginTransaction();
		Query query = session.getNamedQuery("result.find");
		query.setString("var", msisdn);
		Result res = (Result) query.uniqueResult();
		tx.rollback();
		// test result
		assertNotNull(res);
		assertEquals(res.getMemberMsisdn(), msisdn);
	}
}
