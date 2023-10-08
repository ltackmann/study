package org.randompage.samples.misc.aop.common;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.wideplay.warp.jpa.JpaUnit;

public class DependencyModule implements Module {
	@Override
	public void configure(Binder binder) {
		// load persistence configuration
		binder.bindConstant().annotatedWith(JpaUnit.class).to("default");
		// start persistence service
		binder.bind(PersistenceStarter.class).asEagerSingleton();
	}
}
