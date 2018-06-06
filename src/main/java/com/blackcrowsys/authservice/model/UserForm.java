package com.blackcrowsys.authservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserForm implements Serializable {

    private static final long serialVersionUID = -8302460784643897610L;

    private String username;
    private String password;
}
