package org.randompage.bookmarking.backend.security;

import org.randompage.bookmarking.api.domain.Role;
import org.randompage.bookmarking.api.domain.User;
import org.randompage.bookmarking.backend.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

/**
 * Custom user-details-service responsible for handling authentication against our database
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    private static Map<Role, GrantedAuthority> roleMap = new EnumMap<Role, GrantedAuthority>(Role.class);
    static {
        roleMap.put(Role.ADMIN, new GrantedAuthorityImpl("ROLE_ADMIN"));
        roleMap.put(Role.USER, new GrantedAuthorityImpl("ROLE_USER"));
    }

    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " not found.", email);
        }
        final String password = user.getPassword();
        final boolean enabled = true;
        final boolean accountNonExpired = true;
        final boolean credentialsNonExpired = true;
        final boolean accountNonLocked = true;
        Collection<GrantedAuthority> authorities = getAuthorities(user);

        return new org.springframework.security.core.userdetails.User(email, password, enabled,
                accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

    }

    private Collection<GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(roleMap.get(user.getRole()));
        return authorities;
    }


    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
