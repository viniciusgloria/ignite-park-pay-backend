package br.com.ignite.dto;

import br.com.ignite.entity.User;

public record UserDTOListagem(
			Long id,
			String name,
			String email,
			String cpf,
			String phone,
			String dtNascimento,
			Boolean ativo,
			Boolean admin
		) {
	public UserDTOListagem(User user) {
		this(user.getId(), user.getName(), user.getEmail(), user.getCpf(), user.getPhone(), user.getDtNascimento(), user.getAtivo(), user.getAdmin());
	}
}
