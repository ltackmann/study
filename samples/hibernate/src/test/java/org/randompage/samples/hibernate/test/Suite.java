package org.randompage.samples.hibernate.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class Suite {
	public static Test suite() {
		return new TestSuite(TestPersistence.class);
	}

	public static void main(String[] args) {
		TestRunner.run(suite());
	}
}
