package br.com.ignite.entity;

import br.com.ignite.dto.ParkingDTORegister;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PARKING")
@Getter
@Setter
public class Parking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "CNPJ")
	private String cnpj;
	
	@Column(name = "PHONE_COMERCIAL")
	private String phoneComercial;
	
	@Column(name = "DIA_FUNCIONAMENTO")
	private String diaFuncionamento;
	
	@Column(name = "HORARIO_FUNCIONAMENTO")
	private String horarioFuncionamento;
	
	@Column(name = "QTD_VAGAS")
	private Integer qtdVagas;
	
	private Address address;
	
	@Column(name = "ATIVO")
	private Boolean ativo = true;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	public Parking() {
	}
	
	public Parking(ParkingDTORegister dados) {
		this.name = dados.name();
		this.cnpj = dados.cnpj();
		this.phoneComercial = dados.phoneComercial();
		this.diaFuncionamento = dados.diaFuncionamento();
		this.horarioFuncionamento = dados.horarioFuncionamento();
		this.qtdVagas = dados.qtdVagas();
		this.address = new Address(dados.address());
	}
}
