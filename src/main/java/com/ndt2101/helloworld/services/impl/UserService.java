package com.ndt2101.helloworld.services.impl;

import com.ndt2101.helloworld.convertor.UserConvertor;
import com.ndt2101.helloworld.dto.UserDTO;
import com.ndt2101.helloworld.entities.RoleEntity;
import com.ndt2101.helloworld.entities.UserEntity;
import com.ndt2101.helloworld.repository.RoleRepository;
import com.ndt2101.helloworld.repository.UserRepository;
import com.ndt2101.helloworld.services.IUserService;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
        UserEntity mappedUserEntity = new UserEntity();
        if (userDTO.getId() != null) {
            UserEntity oldEntity = userRepository.findById(userDTO.getId()).get();
            mappedUserEntity = userConvertor.toUserEntity(userDTO, oldEntity);
        } else {
            mappedUserEntity = mapper.map(userDTO, UserEntity.class);
        }
        RoleEntity roleEntity = roleRepository.findByName(userDTO.getRoles().get(0));
        ArrayList<RoleEntity> roles = new ArrayList<>();
        roles.add(roleEntity);
        mappedUserEntity.setRoles(roles);
        UserEntity userEntity = userRepository.save(mappedUserEntity);
        return userConvertor.toUserDTO(userEntity);
    }

    @Override
    public List<UserDTO> findAllUser(Pageable pageable) {
        ArrayList<UserDTO> result = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll(pageable).getContent();
        userEntities.forEach(userEntity -> {
            UserDTO userDTO = userConvertor.toUserDTO(userEntity);
            result.add(userDTO);
        });
        return result;
    }

    @Override
    public List<UserDTO> findAllUser() {
        ArrayList<UserDTO> result = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();
        userEntities.forEach(userEntity -> {
            UserDTO userDTO = userConvertor.toUserDTO(userEntity);
            result.add(userDTO);
        });
        return result;
    }

    @Override
    public int totalUser() {
        return (int) userRepository.count();
    }
}
