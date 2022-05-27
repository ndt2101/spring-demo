package com.ndt2101.helloworld.jwt;

public class UsernamePasswordPayload {
    private String username;
    private String password;

    public UsernamePasswordPayload() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
