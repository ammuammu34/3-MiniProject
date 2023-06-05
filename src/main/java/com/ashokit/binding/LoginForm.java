package com.ashokit.binding;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginForm {
	@NotBlank(message = "Email is mandatory")
	private String email;
	@NotBlank(message = "Password is not valid")
	private String password;

}
