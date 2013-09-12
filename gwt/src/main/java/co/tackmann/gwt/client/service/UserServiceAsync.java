package co.tackmann.gwt.client.service;

import co.tackmann.gwt.shared.UserDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
      void authenticate(String email, String password, AsyncCallback<UserDTO> async);

    /**
     * Create user
     */
    void create(UserDTO userDTO, String password, AsyncCallback<Boolean> async);
}
