package com.blackcrowsys.authservice.controllers;

import com.blackcrowsys.authservice.service.AuthenticationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AuthenticationControllerTests {

    @Mock
    private AuthenticationService service;

    @InjectMocks
    private AuthenticationController controller;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
}
