package org.randompage.samples.seam.wiki.action;

import org.apache.commons.codec.digest.DigestUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.randompage.samples.seam.wiki.client.EmptyResultException;
import org.randompage.samples.seam.wiki.client.UserManager;
import org.randompage.samples.seam.wiki.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

@Name("register")
@Scope(ScopeType.EVENT)
public class RegisterAction {
	@Autowired
	private UserManager userManager;
	@In
	private User user;
	@In
	private FacesMessages facesMessages;

	private String verify;
	private boolean registered;

	public String getVerify() {
		return verify;
	}

	public void invalid() {
		facesMessages.add("Please try again");
	}

	public boolean isRegistered() {
		return registered;
	}

	public void register() {
		if (user.getPassword().equals(verify)) {
			try {
				user = userManager.getUser(user.getUsername());
			} catch (EmptyResultException e) {
				userManager.saveUser(user);
				registered = true;
				return;
			}
			facesMessages.addToControl("username",
					"Username #{user.username} already exists");
		} else {
			facesMessages.add("verify", "Re-enter your password");
			verify = null;
		}
	}

	public void setVerify(String verify) {
		this.verify = DigestUtils.md5Hex(verify);
	}

}