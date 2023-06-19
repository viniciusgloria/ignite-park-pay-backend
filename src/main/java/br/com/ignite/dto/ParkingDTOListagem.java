package br.com.ignite.dto;

import br.com.ignite.entity.Address;
import br.com.ignite.entity.Parking;

public record ParkingDTOListagem(
			Long id,
			String name,
			String cnpj,
			String phoneComercial,
			String diaFuncionamento,
			String horarioFuncionamento,
			Integer qtdVagas,
			Address address
		) {
	public ParkingDTOListagem(Parking parking) {
		this(parking.getId(), parking.getName(), parking.getCnpj(), parking.getPhoneComercial(), parking.getDiaFuncionamento(), 
				parking.getHorarioFuncionamento(), parking.getQtdVagas(), parking.getAddress());
	}
}
