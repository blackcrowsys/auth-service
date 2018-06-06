package com.blackcrowsys.authservice.service;

import com.blackcrowsys.authservice.model.BcsUserPrincipal;
import org.springframework.stereotype.Service;

@Service
public class DbAuthenticationService implements AuthenticationService {

    @Override
    public BcsUserPrincipal authenticate(String user, String auth) {
        return null;
    }
}
