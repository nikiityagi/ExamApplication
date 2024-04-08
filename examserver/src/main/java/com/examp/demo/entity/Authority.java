 package com.examp.demo.entity;

import org.springframework.security.core.GrantedAuthority;

//Spring Security File
public class Authority implements GrantedAuthority{
	
	private String authority;
	
	public Authority(String authority)
	{
		this.authority=authority;
	}
	
	@Override
	public String getAuthority()
	{
		return this.authority;
	}

}
