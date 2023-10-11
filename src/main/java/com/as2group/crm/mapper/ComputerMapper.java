package com.as2group.crm.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.as2group.crm.dto.ComputerRequest;
import com.as2group.crm.dto.ComputerResponse;
import com.as2group.crm.dto.EmployeeResponse;
import com.as2group.crm.model.Computer;


@Component
public class ComputerMapper {
	
	@Autowired
	EmployeeMapper employeeMapper;

	public Computer map(ComputerRequest computerRequest) {
		Computer computer = new Computer();
		computer.setPatrimony(computerRequest.patrimony());
		computer.setSn(computerRequest.sn());
		computer.setModel(computerRequest.model());
		computer.setBrand(computerRequest.brand());
		computer.setSoCurrent(computerRequest.soCurrent());
		computer.setSoOriginal(computerRequest.soOriginal());
		return computer;
	}
	
	public ComputerResponse map(Computer computer) {
		EmployeeResponse employee = null;
	    if (computer.getEmployee() != null) {
	        employee = this.employeeMapper.map(computer.getEmployee());
	    }
	    return new ComputerResponse(
	        computer.getId(),
	        computer.getStatus(),
	        computer.getPatrimony(),
	        computer.getSn(),
	        employee,
	        computer.getModel(),
	        computer.getBrand(),
	        computer.getSoCurrent(),
	        computer.getSoOriginal(),
	        computer.getEntryDate(),
	        computer.getDepartureDate(),
	        computer.getModificationDate(),
	        computer.getComputerComponents()
	    );
		
	}
	
	public List<ComputerResponse> map(List<Computer> computers){
		List<ComputerResponse> response = new ArrayList<>();
		for(Computer computer : computers) {
			EmployeeResponse employee = null;
			if (computer.getEmployee() != null) {
				employee = this.employeeMapper.map(computer.getEmployee());
			}
			response.add(new ComputerResponse(
				computer.getId(),
				computer.getStatus(),
				computer.getPatrimony(),
				computer.getSn(),
				employee,
				computer.getModel(),
				computer.getBrand(),
				computer.getSoCurrent(),
				computer.getSoOriginal(),
				computer.getEntryDate(),
				computer.getDepartureDate(),
				computer.getModificationDate(),
				computer.getComputerComponents()
			)); 
		}
		return response;
	}
}
