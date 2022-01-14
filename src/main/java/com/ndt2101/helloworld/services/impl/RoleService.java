package com.ndt2101.helloworld.services.impl;

import com.ndt2101.helloworld.dto.RoleDTO;
import com.ndt2101.helloworld.entities.RoleEntity;
import com.ndt2101.helloworld.services.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by Tuan
 * 12:33
 * 14 Jan 2022
 */

@Service
public class RoleService implements IRoleService {

    @Autowired
    ModelMapper mapper;

    @Override
    public RoleEntity findRoleByName(String roleName) {

        return null;
    }
}
