package org.randompage.samples.seam.wiki.action;

import org.apache.commons.codec.digest.DigestUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;
import org.randompage.samples.seam.wiki.client.EmptyResultException;
import org.randompage.samples.seam.wiki.client.UserManager;
import org.randompage.samples.seam.wiki.domain.LoginLog;
import org.randompage.samples.seam.wiki.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

@Name("authenticator")
@Scope(ScopeType.EVENT)
public class AuthenticatorAction {
	@Autowired
	private UserManager userManager;

	@Logger
	private Log log;

	@In
	private FacesMessages facesMessages;

	@In
	private Identity identity;

	@Out(required = false, scope = ScopeType.SESSION)
	private User user;

	public boolean authenticate() {
		log.debug("authenticating #0", identity.getUsername());
		try {
			user = userManager.getUser(identity.getUsername());
		} catch (EmptyResultException e) {
			facesMessages.add(e.getMessage());
			return false;
		}
		String md5sum = DigestUtils.md5Hex(identity.getPassword());
		if (!md5sum.equalsIgnoreCase(user.getPassword())) {
			facesMessages.add("illegal password");
			return false;
		}
		// login successful, grant access and log to DB
		LoginLog loginLog = new LoginLog();
		loginLog.setUser(user);
		userManager.saveLoginLog(loginLog);
		return true;
	}
}
