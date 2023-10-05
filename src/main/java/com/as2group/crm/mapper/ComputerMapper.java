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
	
	public ComputerResponse map(Computer computer) {
		return new ComputerResponse(computer.getId(), null, null, null, null, null, null, null, null, null, null, null, null);
	}
}
