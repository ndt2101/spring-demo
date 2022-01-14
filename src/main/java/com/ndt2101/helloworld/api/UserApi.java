package com.ndt2101.helloworld.api;

import com.ndt2101.helloworld.dto.UserDTO;
import com.ndt2101.helloworld.entities.UserEntity;
import com.ndt2101.helloworld.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Tuan
 * 12:08
 * 14 Jan 2022
 */
@CrossOrigin
@RestController
public class UserApi {
    @Autowired
    IUserService userService;

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }
}
