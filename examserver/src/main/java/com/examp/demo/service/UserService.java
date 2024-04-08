package com.examp.demo.service;

import java.util.Set;

import com.examp.demo.entity.User;
import com.examp.demo.entity.UserRole;

public interface UserService {
	
	//creating user
	public User createUser(User user,Set<UserRole> userRoles) throws Exception;
	
	//get the user by username
	public User getUserByName(String username);
	
	//delete the user be Id
	public void deleteUser(Long userId);

}
