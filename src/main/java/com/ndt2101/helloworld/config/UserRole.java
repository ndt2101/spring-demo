package com.ndt2101.helloworld.config;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.ndt2101.helloworld.config.UserPermission.*;

public enum UserRole {
    ADMIN(Sets.newHashSet(WRITE, READ, DELETE)),
    STUDENT(Sets.newHashSet(READ)),
    STUDENT_ADMIN(Sets.newHashSet(READ, WRITE));

    public final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
