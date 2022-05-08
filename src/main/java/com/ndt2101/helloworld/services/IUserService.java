package com.ndt2101.helloworld.services;

import com.ndt2101.helloworld.dto.UserDTO;
import com.ndt2101.helloworld.entities.UserEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Create by Tuan
 * 11:26
 * 14 Jan 2022
 */
public interface IUserService {
    UserDTO save(UserDTO userDTO);
    List<UserDTO> findAllUser(Pageable pageable);
    List<UserDTO> findAllUser();
    int totalUser();
}
