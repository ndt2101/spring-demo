package com.ndt2101.helloworld.repository;

import com.ndt2101.helloworld.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by Tuan
 * 12:30
 * 14 Jan 2022
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String roleName);
}
