package org.randompage.samples.spring.security.test.utils;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationManager;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.context.SecurityContextImpl;
import org.springframework.security.providers.ProviderManager;
import org.springframework.security.providers.TestingAuthenticationProvider;
import org.springframework.security.providers.TestingAuthenticationToken;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ProviderManager providerManager;

	public void login(String username, String password) {
		// create authentication request
		Authentication auth = new UsernamePasswordAuthenticationToken(username,password);
		// try to authenticate credentials
		Authentication token = authenticationManager.authenticate(auth);
		store(token);
	}

	// store authenticated token in the security context 
	private void store(Authentication token) {
		SecurityContext secureContext = new SecurityContextImpl();
		secureContext.setAuthentication(token);
		SecurityContextHolder.setContext(secureContext);
	}

	@SuppressWarnings("unchecked")
	public void loginAs(String... roles) {
		final String username = "dummy";
		final String password = "secret";
		// grant roles to token
		List<GrantedAuthority> grants = new LinkedList<GrantedAuthority>();
		for (String role : roles)
			grants.add(new GrantedAuthorityImpl(role));
		Authentication token = new TestingAuthenticationToken(username,
				password, grants.toArray(new GrantedAuthority[0]));
		// enable testing authentication provider
		List<Object> list = providerManager.getProviders();
		if (list == null)
			list = new LinkedList<Object>();
		list.add(new TestingAuthenticationProvider());
		providerManager.setProviders(list);
		// store token with our grants
		store(token);
	}

	public void clearAuth() {
		store(null);
	}
}
