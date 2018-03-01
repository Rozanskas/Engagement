package com.mindaugas.engagementapp.exception;

public class UserBlockedException extends Exception {
	
	public UserBlockedException(){}
	
	public UserBlockedException(String errDescription){
		super(errDescription);
	}
	   

}
