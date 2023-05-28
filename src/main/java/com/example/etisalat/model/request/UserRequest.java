package com.example.etisalat.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
	@NotBlank(message = "Name is Mandatory")
	public String name;
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email is not in proper format e.g. email@domain.com")
	public String email;

	public UserRequest(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

}
