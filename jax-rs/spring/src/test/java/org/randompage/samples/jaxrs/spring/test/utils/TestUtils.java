package org.randompage.samples.jaxrs.spring.test.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.randompage.samples.jaxrs.spring.client.UserManager;
import org.randompage.samples.jaxrs.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.context.SecurityContextImpl;
import org.springframework.security.providers.ProviderManager;
import org.springframework.security.providers.TestingAuthenticationProvider;
import org.springframework.security.providers.TestingAuthenticationToken;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.WebResource.Builder;

@Component
public class TestUtils {
	@Autowired
	private UserManager userManager;
	@Autowired
	private ProviderManager providerManager;
	
	public void setAuth(Builder builder, String username, String password) {
		String credentials = username + ":" + password;
		byte[] base64 = Base64.encodeBase64(credentials.getBytes());
		String header = "Basic " + new String(base64);
		builder.header("Authorization", header);
	}

	/**
	 * Prepare user for use in test
	 * 
	 * @param username
	 * @param password
	 */
	@SuppressWarnings("unchecked")
	public void prepareUser(String username, String password) {
		User user = userManager.getUser(username);
		if (user != null) {
			// grant ourself rights to delete user
			final String[] roles = { "ADMIN" };
			List<GrantedAuthority> grants = new LinkedList<GrantedAuthority>();
			for (String role : roles)
				grants.add(new GrantedAuthorityImpl(role));
			Authentication token = new TestingAuthenticationToken(username,
					password, grants.toArray(new GrantedAuthority[0]));
			// add security manager
			List<Object> list = (providerManager.getProviders() == null) ? new ArrayList<Object>()
					: providerManager.getProviders();
			list.add(new TestingAuthenticationProvider());
			providerManager.setProviders(list);
			// store security context
			SecurityContext secureContext = new SecurityContextImpl();
			secureContext.setAuthentication(token);
			SecurityContextHolder.setContext(secureContext);
			
			userManager.deleteUser(user);
		}
	}
}
