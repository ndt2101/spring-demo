package com.ndt2101.helloworld.services.impl;

import com.ndt2101.helloworld.convertor.ModelMapperConfig;
import com.ndt2101.helloworld.convertor.UserConvertor;
import com.ndt2101.helloworld.dto.RoleDTO;
import com.ndt2101.helloworld.dto.UserDTO;
import com.ndt2101.helloworld.entities.RoleEntity;
import com.ndt2101.helloworld.entities.UserEntity;
import com.ndt2101.helloworld.repository.RoleRepository;
import com.ndt2101.helloworld.repository.UserRepository;
import com.ndt2101.helloworld.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Tuan
 * 11:31
 * 14 Jan 2022
 */

@Service
public class UserService implements IUserService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserConvertor userConvertor;

    @Override
    public UserDTO save(UserDTO userDTO) {
        RoleEntity roleEntity = roleRepository.findByName(userDTO.getRoles().get(0));
        ArrayList<RoleEntity> roles = new ArrayList<>();
        roles.add(roleEntity);
        UserEntity mappedUserEntity = mapper.map(userDTO, UserEntity.class);
        mappedUserEntity.setRoles(roles);
        UserEntity userEntity = userRepository.save(mappedUserEntity);
        return userConvertor.toUserDTO(userEntity);
    }
}
