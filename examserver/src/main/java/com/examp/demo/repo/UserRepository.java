package com.examp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examp.demo.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	public User findByUsername(String username);

}
