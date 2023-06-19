package br.com.ignite.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.ignite.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Page<User> findAllByAtivoTrue(Pageable paginacao);
	
	@Query(value = "select u from User u where u.ativo = 1")
	List<User> findByAtivoTrue();
	
	@Query(value = "select u.email, u.password from User u where u.email = ?1 and u.password = ?2")
	List<User> findByEmailAndPassword(String email, String password);
	
	UserDetails findByEmail(String email);
	
	@Query(value = "select u.cpf from User u where u.cpf = ?1")
	String findByAllCpf(String cpf);
}
