package org.randompage.bookmarking.backend.testUtils;

/**
 * User values for use when unit testing (matches values in UserTestData.xml)
 */
public enum UserData {
    ADMIN_1("admin1@bookmarking.com", "admin1", "ADMIN", 1L),
    USER_1("user1@bookmarking.com", "user1", "USER", 2L),
    USER_2("user2@bookmarking.com", "user2", "USER", 3L);
    public final String email;
    public final String password;
    public final String role;
    public final long id;

    UserData(String email, String password, String role, long id) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.id = id;
    }
}
