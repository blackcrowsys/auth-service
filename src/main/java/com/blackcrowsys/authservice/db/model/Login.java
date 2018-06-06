package com.blackcrowsys.authservice.db.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "LOGIN")
@Data
public class Login extends AbstractUUIDEntity {

    private static final long serialVersionUID = 1780342589613763500L;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ACCOUNTNONEXPIRED")
    private boolean accountNonExpired;

    @Column(name = "ACCOUNTNONLOCKED")
    private boolean accountNonLocked;

    @Column(name = "CREDENTIALSNONEXPIRED")
    private boolean credentialsNonExpired;

    @Column(name = "ENABLED")
    private boolean enabled;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "EMAIL")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "ORGANISATION_ID")
    private Organisation organisation;

}
