package com.java.express.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private long userId;
	

	private String firstName;
	private String lastName;
	private String email;
	private String phone;

}
