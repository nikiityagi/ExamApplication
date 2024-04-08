package com.examp.demo.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examp.demo.entity.User;
import com.examp.demo.entity.UserRole;
import com.examp.demo.helper.UserFoundException;
import com.examp.demo.repo.RoleRepository;
import com.examp.demo.repo.UserRepository;
import com.examp.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User local=this.userRepository.findByUsername(user.getUsername());
		if(local!=null)
		{
			System.out.println("User is already there!!");
			throw new UserFoundException();
		}
		else
		{
			//user create
			for(UserRole ur:userRoles)
			{
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			local=this.userRepository.save(user);
		}
		return local;
	}

	@Override
	public User getUserByName(String username) {
		
		return this.userRepository.findByUsername(username) ;
	}
	
	@Override
	public void deleteUser(Long userId) {
		
		this.userRepository.deleteById(userId);
		
	}
	
	

}
