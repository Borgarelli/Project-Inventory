package com.as2group.crm.mapper;

import org.springframework.stereotype.Component;

import com.as2group.crm.dto.ComputerRequest;
import com.as2group.crm.dto.ComputerResponse;
import com.as2group.crm.model.Computer;

@Component
public class ComputerMapper {

	public Computer map(ComputerRequest computerRequest) {
		Computer computer = new Computer();
		computer.setPatrimony(computerRequest.patrimony());
//		computer.set
		return computer;
	}
	
//	public ComputerResponse map(Computer computer) {
//		return new ComputerResponse(computer.getId(), computer.getStatus(), computer.getPatrimony(), computer.getSn(), computer.getEmployee(), computer.getModel(), computer.getBrand(), computer.getSoCurrent(), computer.getSoOriginal(), computer.getEntryDate(), computer.getDepartureDate(), computer.getModificationDate(), computer.getComputerComponents());
//	}
}
