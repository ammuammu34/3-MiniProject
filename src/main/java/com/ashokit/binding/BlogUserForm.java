package com.ashokit.binding;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class BlogUserForm {
	private Integer userId;
	@NotBlank(message = "Name is mandatory")
	 @Size(message = "Name should have only 3 to 15 Characters")
	private String firstName;
	@NotBlank(message = "Name is mandatory")
	 @Size(message = "Name should have only 3 to 15 Characters")
	private String lastName;
	@NotBlank(message = "Email is mandatory")
	private String email;
	@NotBlank(message = "Password is mandatory")
	private String password;
	
}
