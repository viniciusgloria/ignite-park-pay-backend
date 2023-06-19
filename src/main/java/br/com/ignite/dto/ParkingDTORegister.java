package br.com.ignite.dto;

import jakarta.validation.constraints.NotBlank;

public record ParkingDTORegister(
			@NotBlank
			String name,
			
			@NotBlank
			String cnpj,
			
			@NotBlank
			String phoneComercial,
			
			@NotBlank
			String diaFuncionamento,
			
			@NotBlank
			String horarioFuncionamento,
			
			@NotBlank
			Integer qtdVagas,
			
			AddressDTORegister address
		) {}
