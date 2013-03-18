package org.randompage.bookmarking.frontend.shared;

import org.randompage.bookmarking.api.domain.Role;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private Role role;
    private String email;
    private String name;

    public UserDTO() {
        // Make GWT happy
    }

    public UserDTO(String name, Role role, String email) {
        this();
        setName(name);
        setRole(role);
        setEmail(email);
    }


    public String getEmail() {
        return email;
    }


    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
