package com.as2group.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as2group.crm.model.Computer;
import com.as2group.crm.model.ComputadorFuncionario;
import com.as2group.crm.model.Employee;


public interface ComputadorFuncionarioRepository extends JpaRepository<ComputadorFuncionario, Long> {
	
	
	List<ComputadorFuncionario> findByComputadorAndFuncionario(Computer computador, Employee employee);
	
	List<ComputadorFuncionario> findByComputador(Computer computador);
	
	List<ComputadorFuncionario> findByEmployee(Employee employee);
	
	

}
