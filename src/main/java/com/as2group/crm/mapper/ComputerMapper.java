package com.as2group.crm.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.as2group.crm.dto.ComponentsResponse;
import com.as2group.crm.dto.ComputerRequest;
import com.as2group.crm.dto.ComputerResponse;
import com.as2group.crm.dto.ComputerSimpleResponse;
import com.as2group.crm.dto.EmployeeResponse;
import com.as2group.crm.model.Computer;



@Component
public class ComputerMapper {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	ComponentsMapper componentMapper;

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
		List<ComponentsResponse> component = this.componentMapper.map(computer.getComputerComponents());
		
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
	        component
	    );
	}

	public List<ComputerResponse> map(List<Computer> computers) {
	    List<ComputerResponse> response = new ArrayList<>();
	    for (Computer computer : computers) {
	        EmployeeResponse employee = null;
	        List<ComponentsResponse> component = this.componentMapper.map(computer.getComputerComponents());

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
	            component
	        ));
	    }
	    return response;
	}

	public ComputerSimpleResponse mapSimple(Computer computer) {
		return new ComputerSimpleResponse(computer.getId(), computer.getPatrimony(), computer.getSn());
	}

	public List<ComputerSimpleResponse> mapSimple(List<Computer> computers) {
		List<ComputerSimpleResponse> response = new ArrayList<>();
		for(Computer computer : computers) {
			response.add( new ComputerSimpleResponse(computer.getId(), computer.getPatrimony(), computer.getSn()) );
		}
		return response;
	}

}