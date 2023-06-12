package com.example.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignupForm {
	@NotBlank (groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	private String userEmail;
	
	@NotBlank (groups = ValidGroup1.class)
	@Length (min = 4, max = 100, groups = ValidGroup2.class)
	@Pattern (regexp = "^[a-zA-Z0-9]+$" )
	private String password;
	
	@NotBlank (groups = ValidGroup1.class)
	private String userName;
	
	@NotBlank (groups = ValidGroup1.class)
	private String phone;
	
	@NotBlank (groups = ValidGroup1.class)
	private String address;
}
