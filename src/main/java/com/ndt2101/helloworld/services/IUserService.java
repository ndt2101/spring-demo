package com.ndt2101.helloworld.services;

import com.ndt2101.helloworld.dto.UserDTO;
import com.ndt2101.helloworld.entities.UserEntity;

/**
 * Create by Tuan
 * 11:26
 * 14 Jan 2022
 */
public interface IUserService {
    UserDTO save(UserDTO userDTO);
}
