package org.randompage.bookmarking.frontend.action;

import org.randompage.bookmarking.api.UserManager;
import org.randompage.bookmarking.api.domain.Bookmark;
import org.randompage.bookmarking.api.domain.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class BookmarkAction {
    private Bookmark bookmark;

    // managed properties
    @ManagedProperty(value = "#{userManager}")
    private UserManager userManager;
    @ManagedProperty(value = "#{user}")
    private User user;

    public BookmarkAction() {
        init();
    }

    public Bookmark getBookmark() {
        return bookmark;
    }

    private void init() {
        bookmark = new Bookmark();
    }

    public void setBookmark(Bookmark bookmark) {
        this.bookmark = bookmark;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public String storeMessage() {
        // TODO return failure and success
        //user.addBookmark(bookmark);
        userManager.modifyUser(user);
        init();
        return null;
    }
}
