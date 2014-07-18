package org.randompage.samples.spring.security.impl;

import java.util.List;

import org.randompage.samples.spring.security.domain.Account;
import org.randompage.security.ArgumentAuditor;
import org.randompage.security.ResultAuditor;

public class Auditors {
	public static class AccountAuditor implements ResultAuditor, ArgumentAuditor {
		private String principal;

		@Override
		public void checkResult(Object object) throws SecurityException {
			if (object instanceof List) {
				List list = (List) object;
				for (int i = 0; i < list.size(); i++)
					checkAccount(object);
			} else {
				checkAccount(object);
			}

		}

		private void checkAccount(Object object) {
			if (object instanceof Account) {
				Account account = (Account) object;
				// TODO check that principal has access to account
			} else {
				throw new SecurityException("unhandled object passed to auditor");
			}
		}

		@Override
		public void setPrincipal(Object principal) {
			this.principal = (String) principal;
		}

		@Override
		public void checkAuguments(Object... args) throws SecurityException {
			for (Object object : args)
				checkAccount(object);
		}
	}
}
