package br.com.ignite.entity;

import java.math.BigDecimal;

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
@Table(name = "wallets")
@Getter
@Setter
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
//	@Column(name = "SALDO")
//	private BigDecimal saldo;
	
	@Column(name = "CREDITO")
	private BigDecimal credito;
	
	@Column(name = "DEBITO")
	private BigDecimal debito;
	
	@Column(name = "TIPO_CREDITO")
	private String tipoCredito;
	
	@Column(name = "DT_ENTRADA_CREDITO")
	private String dtEntradaCredito;
	
	@Column(name = "DT_SAIDA_CREDITO")
	private String dtSaidaCredito;
	
//	@Column(name = "SALDO_CASHBACK")
//	private BigDecimal saldoCashback;
	
	@ManyToOne
	@JoinColumn(name = "USERS_ID")
	private User users;
}
