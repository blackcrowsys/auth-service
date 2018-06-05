package com.blackcrowsys.authservice.service;

import com.blackcrowsys.authservice.model.BcsUserPrincipal;

public interface AuthenticationService {

    BcsUserPrincipal authenticate(String user, String auth);
}
