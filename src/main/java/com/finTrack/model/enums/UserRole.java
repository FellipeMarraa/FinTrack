package com.finTrack.model.enums;

public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role) {}

    public String getRole() {
        return role;
    }
}