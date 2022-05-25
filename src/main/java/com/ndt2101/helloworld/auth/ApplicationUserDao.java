package com.ndt2101.helloworld.auth;

import org.springframework.context.annotation.Bean;

import java.util.Optional;

public interface ApplicationUserDao {
    Optional<ApplicationUser> selectUserFromUserName(String userName);
}
