package com.examp.demo.controller;

import java.util.HashSet;
import java.util.Set;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examp.demo.entity.Role;
import com.examp.demo.entity.User;
import com.examp.demo.entity.UserRole;
import com.examp.demo.helper.UserFoundException;
import com.examp.demo.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	//creating user
	@PostMapping("/create")
	public User createUser(@RequestBody User user)throws Exception
	{
		user.setProfile("default.png");
		
		//encoding password with bcryptpasswordencoder
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<UserRole> roles=new HashSet<>();
		
		Role role=new Role();
		role.setRoleId(45l);
		role.setRoleName("NORMAL");
		
		UserRole userRole=new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		roles.add(userRole);
		
		return this.userservice.createUser(user,roles);
	}
	
	//get the user details
	@GetMapping("/{username}")
	public User getUserByName(@PathVariable("username") String username)
	{
		return this.userservice.getUserByName(username);
	}
	
	//delete the user details
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId)
	{
		this.userservice.deleteUser(userId);
	}

	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> exceptionHandler(UserFoundException ex)
	{
		return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	}
}
