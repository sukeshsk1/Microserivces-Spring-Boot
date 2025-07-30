package com.zoshpayload.request;

import com.zosh.domain.UserRole;

import lombok.Data;

@Data
public class SignupDto {
	private String email;
	private String password;
	private String phone;
	private String fullName;
	private String username;
	
	private UserRole role;


}