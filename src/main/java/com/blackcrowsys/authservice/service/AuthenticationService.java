package com.blackcrowsys.authservice.service;

import com.blackcrowsys.authservice.model.BcsUserPrincipal;

/**
 * Interface for authentication service.
 */
public interface AuthenticationService {

    /**
     * Authenticates a given username and password.
     *
     * @param username the user
     * @param password the password
     * @return the principal
     */
    BcsUserPrincipal authenticate(String username, String password);
}
