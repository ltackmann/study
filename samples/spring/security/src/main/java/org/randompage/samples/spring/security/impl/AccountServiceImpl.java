package org.randompage.samples.spring.security.impl;

import java.util.List;

import org.randompage.samples.spring.security.AccountService;
import org.randompage.samples.spring.security.domain.Account;
import org.randompage.samples.spring.security.impl.Auditors.AccountAuditor;
import org.randompage.security.annotations.Auditable;

public class AccountServiceImpl implements AccountService {

	@Override
	@Auditable(resultAuditor = AccountAuditor.class)
	public List<Account> getAccounts(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Auditable(argumentAuditor = AccountAuditor.class)
	public void insertAccount(Account account) {
		// TODO Auto-generated method stub
	}

	// TODO sample that uses both
	// TODO sample that uses roles
	
	@Override
	@Auditable(permitAll = true)
	public List<String> getUsernames() {
		// TODO Auto-generated method stub
		return null;
	}

}
