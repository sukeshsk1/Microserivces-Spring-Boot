package com.jaavexpress.user.dto;

import com.jaavexpress.user.model.RoleBasedAuthority;

import lombok.Data;
@Data
public class CredentialDTO {
	
	private String username;
	//private String password;
	
	
	
	private RoleBasedAuthority roleBasedAuthority;
	
	

}
