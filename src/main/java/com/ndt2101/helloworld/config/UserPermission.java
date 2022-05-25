package com.ndt2101.helloworld.config;

public enum UserPermission {
    READ("permission:read"),
    WRITE("permission:write"),
    DELETE("permission:delete");

    public final String permission;

    UserPermission(String permission) {

        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
