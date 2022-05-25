package com.ndt2101.helloworld.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ndt2101.helloworld.config.UserRole.*;

@Repository("impl")
public class ApplicationUserDaoImpl implements ApplicationUserDao {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserDaoImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectUserFromUserName(String userName) {
        return getUsers()
                .stream()
                .filter(applicationUser -> userName.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getUsers() {
        List<ApplicationUser> list = Lists.newArrayList(
                new ApplicationUser(STUDENT.getAuthorities(),
                        "user1",
                        passwordEncoder.encode("111"),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(ADMIN.getAuthorities(),
                        "user2",
                        passwordEncoder.encode("111"),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(STUDENT_ADMIN.getAuthorities(),
                        "user3",
                        passwordEncoder.encode("111"),
                        true,
                        true,
                        true,
                        true
                )
        );
        return list;
    }
}
