package br.com.ignite.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDTOAuthentication(
			@NotBlank(message = "{email.obrigatorio}")
			String email,
			
			@NotBlank(message = "{password.obrigatorio}")
			String password
		) {}
