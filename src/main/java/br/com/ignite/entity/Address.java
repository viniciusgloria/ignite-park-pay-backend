package br.com.ignite.entity;

import br.com.ignite.dto.AddressDTORegister;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {

	@Column(name = "LOGRADOURO")
	private String logradouro;
	
	@Column(name = "NUMERO")
	private String numero;
	
	@Column(name = "COMPLEMENTO")
	private String complemento;
	
	@Column(name = "BAIRRO")
	private String bairro;
	
	@Column(name = "CIDADE")
	private String cidade;
	
	@Column(name = "UF")
	private String uf;
	
	@Column(name = "CEP")
	private String cep;
	
	public Address() {
	}
	
	
	public Address(AddressDTORegister address) {
		this.logradouro = address.logradouro();
		this.numero = address.numero();
		this.complemento = address.complemento();
		this.bairro = address.bairro();
		this.cidade = address.cidade();
		this.uf = address.uf();
		this.cep = address.cep();
	}


	public void updateInformations(@Valid AddressDTORegister dados) {
		
		if (dados.logradouro() != null) {
			this.logradouro = dados.logradouro();
		}
		
		if (dados.numero() != null) {
			this.numero = dados.numero();
		}
		
		if (dados.complemento() != null) {
			this.complemento = dados.complemento();
		}
		
		if (dados.bairro() != null) {
			this.bairro = dados.bairro();
		}
		
		if (dados.cidade() != null) {
			this.cidade = dados.cidade();
		}
		
		if (dados.uf() != null) {
			this.uf = dados.uf();
		}
		
		if (dados.cep() != null) {
			this.cep = dados.cep();
		}
	}
}
