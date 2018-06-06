package com.blackcrowsys.authservice.db.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORGANISATION")
@Data
public class Organisation extends AbstractUUIDEntity {

    private static final long serialVersionUID = 5730701913088337728L;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STREET")
    private String street;

    @Column(name = "TOWN")
    private String town;

    @Column(name = "COUNTY")
    private String county;

    @Column(name = "POSTCODE")
    private String postcode;

    @OneToMany(mappedBy = "organisation", fetch = FetchType.EAGER)
    private List<Login> logins = new ArrayList<>();

}
