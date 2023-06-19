package br.com.ignite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ignite.dto.UserDTOListagem;
import br.com.ignite.dto.UserDTORegister;
import br.com.ignite.dto.UserDTOUpdate;
import br.com.ignite.entity.User;
import br.com.ignite.repository.UserRepository;
import br.com.ignite.service.TokenJWTService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserRepository usersRepository;
	
	@Autowired
	private TokenJWTService tokenService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	@Transactional
	public ResponseEntity<UserDTORegister> register(@RequestBody @Valid UserDTORegister dados) {
		var newUser = new User(dados);
		usersRepository.saveAndFlush(newUser);
		
		return ResponseEntity.ok(dados);	
	}
	
	@PostMapping("/login")
	@Transactional
	public ResponseEntity<String> login(@RequestBody @Valid User dados) {
		try {
			var loginAndPassword = usersRepository.findByEmailAndPassword(dados.getEmail(), dados.getPassword());
			
			var authenticationToken = new UsernamePasswordAuthenticationToken(dados.getEmail(), dados.getPassword());
			var authentication = authenticationManager.authenticate(authenticationToken);
			var tokenJWT = tokenService.generationToken((User) authentication.getPrincipal());
			
			if (loginAndPassword != null || tokenJWT.trim().isEmpty()) {
				return new ResponseEntity<String>("Token gerado: " + tokenJWT, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("usuario/senha invalido", HttpStatus.UNAUTHORIZED);
			}			
		} catch(AuthenticationException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping("/users")
	public ResponseEntity<Page<UserDTOListagem>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable paginacao) {
		var pageUsers = usersRepository.findAllByAtivoTrue(paginacao).map(UserDTOListagem::new);
		return ResponseEntity.ok(pageUsers);
	}
	
	@PutMapping("/update-users")
	@Transactional
	public ResponseEntity<UserDTOUpdate> updateUsers(@RequestBody @Valid UserDTOUpdate dados) {
		var updateUser = usersRepository.getReferenceById(dados.id());
		updateUser.updateInformations(dados);
		
		return ResponseEntity.ok(dados);
	}
	
	@DeleteMapping("/delete-users/{id}")
	@Transactional
	public ResponseEntity<String> deleteUsers(@PathVariable Long id) {
		var deleteUser = usersRepository.getReferenceById(id);
		deleteUser.inativar();
		
		return ResponseEntity.noContent().build();
	}
}
