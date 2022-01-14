package com.ndt2101.helloworld.services;

import com.ndt2101.helloworld.dto.RoleDTO;
import com.ndt2101.helloworld.entities.RoleEntity;

/**
 * Create by Tuan
 * 12:32
 * 14 Jan 2022
 */
public interface IRoleService {
    RoleEntity findRoleByName(String roleName);
}
