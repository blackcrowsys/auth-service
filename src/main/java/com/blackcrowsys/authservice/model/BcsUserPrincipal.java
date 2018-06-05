package com.blackcrowsys.authservice.model;

import lombok.Data;

import java.util.Map;

@Data
public class BcsUserPrincipal {

    private String username;

    private String jwtToken;

    private boolean authenticated;

    private UserType type;

    private Map<String, String> permissions;
}
