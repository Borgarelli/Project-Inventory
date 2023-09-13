package com.as2group.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as2group.crm.model.Computador;
import com.as2group.crm.model.ComputadorFuncionario;
import com.as2group.crm.model.Funcionario;

public interface ComputadorFuncionarioRepository extends JpaRepository<ComputadorFuncionario, Long> {
	
	
	List<ComputadorFuncionario> findByComputadorAndFuncionario(Computador computador, Funcionario funcionario);
	
	List<ComputadorFuncionario> findByComputador(Computador computador);
	
	List<ComputadorFuncionario> findByFuncionario(Funcionario funcionario);
	

}
