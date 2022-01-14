package com.ndt2101.helloworld.convertor;

import com.ndt2101.helloworld.dto.UserDTO;
import com.ndt2101.helloworld.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Create by Tuan
 * 15:47
 * 14 Jan 2022
 */
@Component
public class UserConvertor {

    public UserDTO toUserDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setHomeTown(entity.getHomeTown());
        ArrayList<String> roles = new ArrayList<>();
        entity.getRoles().forEach(roleEntity -> {
            roles.add(roleEntity.getName());
        });
        dto.setRoles(roles);
        return dto;
    }
}
