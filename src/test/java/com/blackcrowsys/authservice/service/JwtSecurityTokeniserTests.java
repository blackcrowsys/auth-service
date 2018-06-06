package com.blackcrowsys.authservice.service;

import com.blackcrowsys.authservice.db.model.Login;
import com.blackcrowsys.authservice.db.model.UserType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;

public class JwtSecurityTokeniserTests {

    private static final String USER = "username";
    private static final String JWT = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsIlR5cGUiOiJST09NIn0.qfKoCc2-UKPBNA22rEYn7_2-A_Obz7k4r6YwUAaPmSQmzN_bRxFKEyOfukh12iJAA-bOwHZsOdibxXDPMkbe4A";

    @InjectMocks
    private JwtSecurityTokeniser tokeniser;

    private Login login;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(tokeniser, "key", "Password1234");

        login = new Login();
        login.setUsername(USER);
        login.setUserType(UserType.ROOM);
    }

    @Test
    public void testTokenisingString() {
        String jwt = tokeniser.tokenise(login);
        assertEquals(JWT, jwt);
    }
}
