package com.as2group.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as2group.crm.model.ComputerComponent;
import com.as2group.crm.model.Computer;
import com.as2group.crm.model.Components;

public interface ComputerComponentRepository extends JpaRepository<ComputerComponent, Long>{

	List<ComputerComponent> findByComputer(Computer computer);
	
	List<ComputerComponent> findByComputerAndComponent(Computer computer, Components component);
	
	List<ComputerComponent> findByComponent(Components component);
}
