package br.com.ignite.dto;

import jakarta.validation.constraints.NotNull;

public record UserDTOUpdate (
			@NotNull
			Long id,
			String name,
			String email,
			String phone,
			String password,
			AddressDTORegister address
		) {}
