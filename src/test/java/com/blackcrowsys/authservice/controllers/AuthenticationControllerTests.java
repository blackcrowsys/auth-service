package com.blackcrowsys.authservice.controllers;

import com.blackcrowsys.authservice.exceptions.UnauthorisedException;
import com.blackcrowsys.authservice.model.BcsUserPrincipal;
import com.blackcrowsys.authservice.model.UserForm;
import com.blackcrowsys.authservice.model.UserType;
import com.blackcrowsys.authservice.service.AuthenticationService;
import com.blackcrowsys.commonutils.json.JacksonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationControllerTests {

    private static final String USER = "username@com";
    private static final String AUTH = "password";
    private static final String AUTH_URL = "/authenticate";

    @Mock
    private AuthenticationService service;

    @InjectMocks
    private AuthenticationController controller;

    private MockMvc mvc;
    private ObjectMapper mapper;
    private BcsUserPrincipal principal;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new AuthenticationExceptionHandler())
                .build();

        mapper = new ObjectMapper();

        principal = new BcsUserPrincipal();
        principal.setUsername(USER);
        principal.setAuthenticated(true);
        principal.setType(UserType.ROOM);
        principal.setJwtToken("1234:1234:1234");
    }

    @Test
    public void testSendingUserAuthenticationForm() throws Exception {
        UserForm form = new UserForm();
        form.setUsername(USER);
        form.setPassword(AUTH);

        when(service.authenticate(USER, AUTH)).thenReturn(principal);

        mvc.perform(post(AUTH_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JacksonConverter.convertToJsonBytes(mapper, form)))
                .andExpect(status().isOk())
                .andExpect(header().string("Authorization", "Bearer " + principal.getJwtToken()))
                .andExpect(header().string("Type", "Room"));

        verify(service).authenticate(USER, AUTH);
    }

    @Test
    public void testHandlingUnsuccessfulAuthentication() throws Exception {
        UserForm form = new UserForm();
        form.setUsername(USER);
        form.setPassword(AUTH);

        when(service.authenticate(USER, AUTH)).thenThrow(new UnauthorisedException());

        mvc.perform(post(AUTH_URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JacksonConverter.convertToJsonBytes(mapper, form)))
                .andExpect(status().isUnauthorized());
    }
}
