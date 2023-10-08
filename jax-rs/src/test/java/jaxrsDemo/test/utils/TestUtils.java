package jaxrsDemo.test.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import jaxrsDemo.client.UserManager;
import jaxrsDemo.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.TestingAuthenticationProvider;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Invocation.Builder; 

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
	public void prepareUser(String username, String password) {
		User user = userManager.getUser(username);
		if (user != null) {
			// grant ourself rights to delete user
			final String[] roles = { "ADMIN" };
			List<GrantedAuthority> grants = new LinkedList<GrantedAuthority>();
			for (String role : roles)
				grants.add(new SimpleGrantedAuthority(role));
			Authentication token = new TestingAuthenticationToken(username, password, grants);
			// add security manager
			List<AuthenticationProvider> list = (providerManager.getProviders() == null) ? new ArrayList<AuthenticationProvider>()
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
