package jaxrsDemo.resources.converters;

import jaxrsDemo.domain.User;

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
