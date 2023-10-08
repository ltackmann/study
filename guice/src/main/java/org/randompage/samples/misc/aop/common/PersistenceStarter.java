package org.randompage.samples.misc.aop.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.PersistenceService;

class PersistenceStarter {

	@Inject
	public PersistenceStarter(PersistenceService service) {
		service.start();
	}
}
