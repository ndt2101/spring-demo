package com.ndt2101.helloworld.repository;

import com.ndt2101.helloworld.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by Tuan
 * 11:19
 * 14 Jan 2022
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntityByName(String name);
}
