package ru.javaops.bootjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
    @JsonProperty("user")
    USER,
    @JsonProperty("admin")
    ADMIN;
}
