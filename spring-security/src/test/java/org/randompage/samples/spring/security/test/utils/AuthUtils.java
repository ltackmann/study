package org.randompage.samples.spring.security.test.utils;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.TestingAuthenticationProvider;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

	public void loginAs(String... roles) {
		final String username = "dummy";
		final String password = "secret";
		// grant roles to token
		List<GrantedAuthority> grants = new LinkedList<GrantedAuthority>();
		for (String role : roles)
			grants.add(new SimpleGrantedAuthority(role));
		Authentication token = new TestingAuthenticationToken(username, password, grants);
		// enable testing authentication provider
		List<AuthenticationProvider> list = providerManager.getProviders();
		if (list == null)
			list = new LinkedList<AuthenticationProvider>();
		list.add(new TestingAuthenticationProvider());
		providerManager.setProviders(list);
		// store token with our grants
		store(token);
	}

	public void clearAuth() {
		store(null);
	}
}
