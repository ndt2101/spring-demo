package com.ndt2101.helloworld.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserServices implements UserDetailsService {

    private final ApplicationUserDao applicationUserDao;

    @Autowired
    public ApplicationUserServices(@Qualifier("impl") ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserDao.selectUserFromUserName(username)
                .orElseThrow(() -> new RuntimeException(username + "is not found"));
    }
}
