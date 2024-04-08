package com.examp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examp.demo.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

}
