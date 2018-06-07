package com.blackcrowsys.authservice.service;

import com.blackcrowsys.authservice.db.model.Login;
import com.blackcrowsys.authservice.db.repository.LoginRepository;
import com.blackcrowsys.authservice.model.BcsUserPrincipal;
import com.blackcrowsys.authservice.model.UserType;
import com.blackcrowsys.commonutils.exceptions.UnauthorisedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Class used to check user credentials against the database.
 */
@Service
public class DbAuthenticationService implements AuthenticationService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private SecurityTokeniser tokeniser;

    @Autowired
    private CachingService cachingService;

    @Override
    public BcsUserPrincipal authenticate(String user, String password) {
        Login login = loginRepository.findByUsername(user).orElseThrow(UnauthorisedException::new);
        BcsUserPrincipal principal = convert(login);
        if (login.isEnabled() && verifyPassword(login, password)) {
            principal.setAuthenticated(true);
            principal.setJwtToken(generateSecurityToken(login));
            cachingService.put(principal.getJwtToken(), principal);
            return principal;
        }
        throw new UnauthorisedException();
    }

    private String generateSecurityToken(Login login) {
        return tokeniser.tokenise(login);
    }

    private boolean verifyPassword(Login login, String password) {
        return encoder.matches(password, login.getPassword());
    }

    private BcsUserPrincipal convert(Login login) {
        BcsUserPrincipal principal = new BcsUserPrincipal();
        principal.setUsername(login.getUsername());
        principal.setType(UserType.getUserType(login.getUserType().name()));
        return principal;
    }
}
