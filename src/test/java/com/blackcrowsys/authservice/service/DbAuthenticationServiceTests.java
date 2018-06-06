package com.blackcrowsys.authservice.service;

import com.blackcrowsys.authservice.db.model.Login;
import com.blackcrowsys.authservice.model.UserType;
import com.blackcrowsys.authservice.db.repository.LoginRepository;
import com.blackcrowsys.authservice.model.BcsUserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DbAuthenticationServiceTests {

    private static final String USER = "userid";
    private static final String USER_PWD = "Password1234";
    private static final String TOKEN = "security_token";

    private Login login;

    @Mock
    private LoginRepository repository;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private SecurityTokeniser tokeniser;

    @Mock
    private CachingService<String, BcsUserPrincipal> cachingService;

    @InjectMocks
    private DbAuthenticationService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUpLogin();

        when(encoder.matches(USER_PWD, USER_PWD)).thenReturn(true);
        when(tokeniser.tokenise(login)).thenReturn(TOKEN);
    }

    @Test
    public void testGettingUserPrincipalByUsername() {
        BcsUserPrincipal principal = service.authenticate(USER, USER_PWD);

        assertNotNull(principal);
        assertEquals(USER, principal.getUsername());
        assertEquals(UserType.ROOM, principal.getType());
        assertTrue(principal.isAuthenticated());
        assertEquals(TOKEN, principal.getJwtToken());
        verify(cachingService).put(TOKEN, principal);
    }

    private void setUpLogin() {
        login = new Login();
        login.setUsername(USER);
        login.setPassword(USER_PWD);
        login.setUserType(com.blackcrowsys.authservice.db.model.UserType.ROOM);
        login.setEnabled(true);
        when(repository.findByUsername(USER)).thenReturn(Optional.of(login));
    }
}
