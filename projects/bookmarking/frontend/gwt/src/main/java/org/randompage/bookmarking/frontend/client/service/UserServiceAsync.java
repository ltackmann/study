package org.randompage.bookmarking.frontend.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.randompage.bookmarking.frontend.shared.UserDTO;

public interface UserServiceAsync {
      void authenticate(String email, String password, AsyncCallback<UserDTO> async);

    /**
     * Create user
     */
    void create(UserDTO userDTO, String password, AsyncCallback<Boolean> async);
}
