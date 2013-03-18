package org.randompage.samples.jaxrs.spring.resources.converters;

import org.randompage.samples.jaxrs.spring.domain.User;
import org.randompage.samples.jaxrs.spring.model.UserBean;
import org.randompage.samples.jaxrs.spring.model.UserRole;

public class UserConverter  {
	public static UserBean fromUser(User user) {
		UserBean userBean = new UserBean();
		// only set properties allowed to showed (i.e. leave out password...)
		userBean.setId(user.getId());
		userBean.setName(user.getName());
		userBean.setRole(UserRole.fromValue(user.getRole().name()));
		userBean.setUsername(user.getUsername());
		return userBean;
	}
}
