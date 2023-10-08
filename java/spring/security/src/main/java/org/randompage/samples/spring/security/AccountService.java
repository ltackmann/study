package org.randompage.samples.spring.security;

import java.util.List;

import org.randompage.samples.spring.security.domain.Account;

public interface AccountService {
    void insertAccount(Account account);

    List<Account> getAccounts(String name);
    
    List<String> getUsernames();
}
