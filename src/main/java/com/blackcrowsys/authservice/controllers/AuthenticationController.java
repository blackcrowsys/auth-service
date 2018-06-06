package com.blackcrowsys.authservice.controllers;

import com.blackcrowsys.authservice.model.BcsUserPrincipal;
import com.blackcrowsys.authservice.model.UserForm;
import com.blackcrowsys.authservice.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@EnableEurekaClient
public class AuthenticationController {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER = "Bearer";
    private static final String TYPE_HEADER = "Type";

    @Autowired
    private AuthenticationService service;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<BcsUserPrincipal> authenticate(@Valid @RequestBody UserForm user, HttpServletResponse response) {
        BcsUserPrincipal principal = service.authenticate(user.getUsername(), user.getPassword());
        response.setHeader(AUTH_HEADER, String.format("%s %s", BEARER, principal.getJwtToken()));
        response.setHeader(TYPE_HEADER, principal.getType().getType());
        return new ResponseEntity<>(principal, HttpStatus.OK);
    }
}
