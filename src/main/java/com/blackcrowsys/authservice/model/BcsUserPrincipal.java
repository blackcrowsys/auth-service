package com.blackcrowsys.authservice.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class BcsUserPrincipal implements Serializable {

    private static final long serialVersionUID = -3368897554281640125L;

    private String username;

    private String jwtToken;

    private boolean authenticated;

    private UserType type;

    private Map<String, String> permissions;
}
