package br.com.ignite.dto;

import br.com.ignite.entity.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserDTORegister(
			@NotBlank(message = "{name.obrigatorio}")
			String name,
			
			@NotBlank(message = "{email.obrigatorio}")
			String email,
			
			@NotBlank(message = "cpf.obrigatorio")
			@Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}")
			String cpf,
			
			@NotBlank(message = "{phone.obrigatorio}")
			String phone,
			
			@NotBlank(message = "{password.obrigatorio}")
			String password,
			
			String dtNascimento,
			
			Address address
		) {}
