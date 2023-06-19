package br.com.ignite.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ignite.entity.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
	
	Page<Parking> findAllByAtivoTrue(Pageable paginacao);
	
	@Query(value = "select p from Parking p where p.ativo = 1")
	List<Parking> findAllByAtivo();
}
