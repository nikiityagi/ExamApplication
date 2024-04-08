package com.examp.demo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.examp.demo.entity.Role;
import com.examp.demo.entity.User;
import com.examp.demo.entity.UserRole;
import com.examp.demo.service.UserService;

@SpringBootApplication
public class ExamserverApplication {
	
//	implements CommandLineRunner 
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ExamserverApplication.class, args);
		System.out.println("My application");
	}

//	@Override
//	public void run(String... args) throws Exception {
//       
//		System.out.println("My application");
//		
//		User user=new User();
//		user.setFirstname("A");
//		user.setLastname("T");
//		user.setUsername("nt2000");
//		user.setPassword(this.bCryptPasswordEncoder.encode("nt"));
//		user.setEmail("n.t@gmail.com");
//		user.setPhone("1111122222");
//		user.setProfile("default.png");
//		
//		Role role=new Role();
//		role.setRoleId(44L);
//		role.setRoleName("ADMIN");
//		
//		Set<UserRole> userRoleSet=new HashSet<>();
//		
//		UserRole userRole=new UserRole();
//		userRole.setUser(user);
//		userRole.setRole(role);
//		
//		User user1=this.userService.createUser(user,userRoleSet);
//		System.out.println(user.getUsername());
		
	}

//}
