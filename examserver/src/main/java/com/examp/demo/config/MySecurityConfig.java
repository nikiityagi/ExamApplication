package com.examp.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled=true)
public class MySecurityConfig{
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private com.examp.demo.service.impl.UserDetailsServiceImpl UserDetailsServiceImpl;
	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(UserDetailsServiceImpl);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
	{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer()
	{
		return ((web)-> web.ignoring().requestMatchers("/myapp/gettoken"));
	}
	
//	protected void configure(AuthenticationManagerBuilder auth)throws Exception
//	{
//		auth.userDetailsService(this.UserDetailsServiceImpl).passwordEncoder(passwordEncoder());
//		
//	}
	
//	protected void configure(HttpSecurity http)throws Exception
//	{
//		http
//		.csrf(AbstractHttpConfigurer::disable)
//	    .cors(AbstractHttpConfigurer::disable)
//		.authorizeHttpRequests(request->
//		      request.requestMatchers("/generate-token","/user/")
//		             .permitAll()
//			         .requestMatchers(HttpMethod.OPTIONS).permitAll()
//			         .anyRequest()
//			         .authenticated())
//		.exceptionHandling(config->config.authenticationEntryPoint(unauthorizedHandler))
//		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		
//		http.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
//	}
//	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception
	{
		System.out.println("Nikitaaaa Rockingggg !!");
		http
		.csrf(AbstractHttpConfigurer::disable)
		.cors(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests((request)->
		           request.requestMatchers(HttpMethod.OPTIONS)
		                  .permitAll()
		                  .requestMatchers("/myapp/gettoken","/user/")
		                  .permitAll()
		                  .anyRequest()
		                  .authenticated())
		.exceptionHandling(config->config.authenticationEntryPoint(unauthorizedHandler))
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	    http.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}

}
