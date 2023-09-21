package com.as2group.crm.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as2group.crm.model.Computer;
import com.as2group.crm.model.ComputadorFuncionario;
import com.as2group.crm.model.Funcionario;
import com.as2group.crm.repository.ComputadorFuncionarioRepository;

@Service
public class ComputadorFuncionarioService {

	@Autowired
	private ComputadorFuncionarioRepository computadorFuncionarioRepository;
	@Autowired
	private ComputerService computadorService;
	@Autowired
	private FuncionarioService funcionarioService;

	public ComputadorFuncionario relacionar(Long computadorId, Long funcionarioId) {
		Computer computador = computadorService.apresentar(computadorId);
		Funcionario funcionario = funcionarioService.apresentar(funcionarioId);

		ComputadorFuncionario relacionamento = new ComputadorFuncionario();

		relacionamento.setComputador(computador);
		relacionamento.setFuncionario(funcionario);
		relacionamento.setRecebidoEm(LocalDateTime.now());
		computador.setFuncionario(funcionario);
		computadorService.alterarStatus(computador, Computer.Status.EM_USO);

		return computadorFuncionarioRepository.save(relacionamento);
	}

	
	public void desvinculo(Long computadorId, Long funcionarioId) {
	    Computer computador = computadorService.apresentar(computadorId);
	    Funcionario funcionario = funcionarioService.apresentar(funcionarioId);

	    List<ComputadorFuncionario> vinculos = computadorFuncionarioRepository
	            .findByComputadorAndFuncionario(computador, funcionario);
	    
	    for (ComputadorFuncionario computadorFuncionario : vinculos) {
	        if (computadorFuncionario.getDevolvidoEm() == null) {
	        	computador.setFuncionario(null);
	            computadorFuncionario.setDevolvidoEm(LocalDateTime.now());
	            computadorFuncionarioRepository.save(computadorFuncionario); 
	            computadorService.alterarStatus(computador, Computer.Status.PRA_USO);
	        }
	    }
	}


	public void desvincular(Long computadorId) {
		Computer computador = computadorService.apresentar(computadorId);

		List<ComputadorFuncionario> vinculos = computadorFuncionarioRepository.findByComputador(computador);
		for (ComputadorFuncionario computadorFuncionario : vinculos) {
			computadorFuncionario.setDevolvidoEm(LocalDateTime.now());
			computadorService.alterarStatus(computador, Computer.Status.PRA_USO);
			computadorFuncionarioRepository.save(computadorFuncionario);
		}

	}

	public List<ComputadorFuncionario> historicoComputador(Long computadorId) {
		Computer computador = computadorService.apresentar(computadorId);
		return computadorFuncionarioRepository.findByComputador(computador);
	}

	public List<ComputadorFuncionario> historicoFuncionario(Long funcionarioId){
		Funcionario funcionario = funcionarioService.apresentar(funcionarioId);
		return computadorFuncionarioRepository.findByFuncionario(funcionario);
	}

}