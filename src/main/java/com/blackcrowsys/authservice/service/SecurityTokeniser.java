package com.blackcrowsys.authservice.service;

import com.blackcrowsys.authservice.db.model.Login;

/**
 * Security tokeniser service.
 */
public interface SecurityTokeniser {

    /**
     * Tokenises a login credential to security token.
     * @param login the login
     * @return security token
     */
    String tokenise(Login login);
}
