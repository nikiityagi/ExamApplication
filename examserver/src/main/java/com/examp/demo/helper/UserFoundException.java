package com.examp.demo.helper;

public class UserFoundException extends Exception{
	
	public UserFoundException()
	{
		super("User with this username is already there in DB !! try with an ");
	}
	
	public UserFoundException(String msg)
	{
		super(msg);
	}

}
