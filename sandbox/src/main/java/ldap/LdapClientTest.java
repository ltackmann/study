package ldap;

import java.util.Hashtable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.junit.Test;

public class LdapClientTest {
	private static final String LDAP_SERVER = "ldap://localhost:10389";
	private static final SearchControls searchControls = new SearchControls();
	static {
		// LDAP attributes we search for
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		searchControls.setReturningAttributes(new String[] { 
				"memberOf",
				"cn" 
		});
	}

	@Test
	public void userLookupTest() throws NamingException {
		LdapContext ldapContext = connectToLdap("uid=admin,ou=system", "secret");
		assertThat(ldapContext, is(notNullValue()));
		
		Attributes attributes = lookupUserAttributes("u0001", ldapContext);
		assertThat((String)attributes.get("cn").get(), equalTo("Lars Tackmann"));
		// TODO test group membership
		System.out.println(attributesToString(attributes));
	}

	private LdapContext connectToLdap(final String userPrincipal, final String password) {
		LdapContext ctx = null;
		try {
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.SECURITY_AUTHENTICATION, "Simple");
			env.put(Context.SECURITY_PRINCIPAL, userPrincipal);
			env.put(Context.SECURITY_CREDENTIALS, password);
			env.put(Context.PROVIDER_URL, LDAP_SERVER);
			ctx = new InitialLdapContext(env, null);
		} catch (NamingException nex) {
			throw new SecurityException("user authentication failed for " + userPrincipal, nex);
		}
		return ctx;
	}

	private Attributes lookupUserAttributes(String userId, LdapContext ldapContext) {
		SearchResult result = null;
		try {
			NamingEnumeration<SearchResult> ldapData = ldapContext.search(
					"ou=users,ou=system",
					"(uid=" + userId + ")",
					searchControls);
			result = (SearchResult) ldapData.next();
			if(ldapData.hasMore()) {
				throw new IllegalArgumentException("unexpected ldap result - to many arguments");
			}
		} catch (NamingException e) {
			throw new SecurityException("ldap search failed for " + userId);
		}
		
		return result.getAttributes();
	}
	
	private String attributesToString(Attributes attributes) {
		NamingEnumeration<? extends Attribute> iterator = attributes.getAll();
		String result = "";
		try {
			while (iterator.hasMore()) {
				Attribute attribute = (Attribute) iterator.next();
				result += "\nattribute id: " + attribute.getID();
				NamingEnumeration<?> values = attribute.getAll();
				while (values.hasMore()) {
					result += "\n\t with value: " + values.next();
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("unable to read attributes");
		}
		//System.out.println(result);
		return result;
	}
}
