package br.com.ignite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTORegister(
			@NotBlank
			String logradouro,
			
			String numero,
			
			String complemento,
			
			@NotBlank
			String bairro,
			
			@NotBlank
			String cidade,
			
			@NotBlank
			String uf,
			
			@NotBlank
			@Pattern(regexp = "\\d{8}")
			String cep
		) {}
