package com.ndt2101.helloworld.convertor;

import com.ndt2101.helloworld.dto.UserDTO;
import com.ndt2101.helloworld.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public UserEntity toUserEntity(UserDTO dto, UserEntity entity) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            entity.setName(dto.getName());
            entity.setDateOfBirth(format.parse(dto.getDateOfBirth()));
            entity.setHomeTown(dto.getHomeTown());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
