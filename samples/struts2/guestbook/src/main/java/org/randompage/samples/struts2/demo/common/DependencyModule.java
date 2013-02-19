package org.randompage.samples.struts2.demo.common;

import org.randompage.samples.struts2.demo.client.UserManager;
import org.randompage.samples.struts2.demo.impl.UserManagerBean;

import com.google.inject.Binder;
import com.google.inject.Module;

public class DependencyModule implements Module {
	public void configure(Binder binder) {
		binder.bind(UserManager.class).to(UserManagerBean.class);
		// TODO use a guice interceptor
	}
}
