package com.blackcrowsys.authservice.model;

import java.util.stream.Stream;

public enum UserType {

    STAFF("Staff"), ROOM("Room");

    private String type;

    private UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public UserType getUserType(String code) {
        return Stream.of(UserType.values())
                .filter(s -> s.type.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
