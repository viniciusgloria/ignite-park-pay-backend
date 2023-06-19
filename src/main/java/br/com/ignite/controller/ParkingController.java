package br.com.ignite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ignite.dto.ParkingDTOListagem;
import br.com.ignite.dto.ParkingDTORegister;
import br.com.ignite.entity.Parking;
import br.com.ignite.repository.ParkingRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/")
public class ParkingController {

	@Autowired
	private ParkingRepository parkingRepository;
	
	@PostMapping("/register-parking")
	@Transactional
	public ResponseEntity<ParkingDTOListagem> registerParking(@RequestBody @Valid ParkingDTORegister dados) {
		var newParking = new Parking(dados);
		parkingRepository.saveAndFlush(newParking);
		
		return new ResponseEntity(dados, HttpStatus.CREATED);
	}
	
	@GetMapping("/parkings")
	public ResponseEntity<Page<ParkingDTOListagem>> listParking(@PageableDefault(size = 10, sort = {"name"}) Pageable paginacao) {
		var pageParkings = parkingRepository.findAllByAtivoTrue(paginacao).map(ParkingDTOListagem::new);
		
		return new ResponseEntity<Page<ParkingDTOListagem>>(pageParkings, HttpStatus.OK);
	}
}
