package br.com.ignite.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.ignite.dto.UserDTORegister;
import br.com.ignite.dto.UserDTOUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "CPF", unique = true)
	private String cpf;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "DT_NASCIMENTO")
	private String dtNascimento;
	
	@Column(name = "ATIVO")
	private Boolean ativo;
	
	@Column(name = "ADMIN")
	private Boolean admin;
	
	private Address address;
	
	@OneToMany(mappedBy = "user")
	private List<Parking> parking;
	
	@OneToMany(mappedBy = "users")
	private List<Wallet> wallet;
	
	public User() {
	}

	public User(UserDTORegister dados) {
		this.name = dados.name();
		this.email = dados.email();
		this.cpf = dados.cpf();
		this.phone = dados.phone();
		this.password = dados.password();
		this.dtNascimento = dados.dtNascimento();
//		this.address = new Address(dados.address());
		this.ativo = true;
		this.admin = false;
	}

	public void updateInformations(@Valid UserDTOUpdate dados) {
		
		if (dados.name() != null) {
			this.name = dados.name();
		}
		
		if (dados.email() != null) {
			this.email = dados.email();
		}
		
//		if (dados.cpf() != null) {
//			this.cpf = dados.cpf();
//		}
		
		if (dados.phone() != null) {
			this.phone = dados.phone();
		}
		
		if (dados.password() != null) {
			this.password = dados.password();
		}
		
//		if (dados.dtNascimento() != null) {
//			this.dtNascimento = dados.dtNascimento();
//		}
		
		if (dados.address() != null) {
			this.address.updateInformations(dados.address());
		}
	}

	public void inativar() { this.ativo = false; }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
