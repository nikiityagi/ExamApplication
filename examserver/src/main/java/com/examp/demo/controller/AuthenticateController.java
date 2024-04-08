package com.examp.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examp.demo.config.JwtUtil;
import com.examp.demo.entity.JwtRequest;
import com.examp.demo.entity.JwtResponse;
import com.examp.demo.entity.User;
import com.examp.demo.service.impl.UserDetailsServiceImpl;

@RestController
@RequestMapping("/myapp")
@CrossOrigin("*")
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailService;
	
	@Autowired
	private JwtUtil jwtUtils;
	
	//generate token
	@PostMapping("/gettoken")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		try
		{
			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		}
		catch(UsernameNotFoundException e)
		{
			e.printStackTrace();
			throw new Exception("User not found");
		}
		
		//authenticate
		UserDetails userDetails=this.userDetailService.loadUserByUsername(jwtRequest.getUsername());
		String token =this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username,String password) throws Exception
	{
		try
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		}
		catch(DisabledException e)
		{
			throw new Exception("USER DISABLED"+e.getMessage());
		}
		catch(BadCredentialsException e)
		{
			throw new Exception("Invalid Credentials"+ e.getMessage());
		}
	}
	
	@GetMapping("/curruser")
	public User getCurrentUser(Principal principal)
	{
		return ((User)this.userDetailService.loadUserByUsername(principal.getName()));
	}

}
