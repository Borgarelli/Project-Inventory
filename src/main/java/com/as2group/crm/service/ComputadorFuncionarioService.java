package com.as2group.crm.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as2group.crm.model.Computador;
import com.as2group.crm.model.ComputadorFuncionario;
import com.as2group.crm.model.Funcionario;
import com.as2group.crm.repository.ComputadorFuncionarioRepository;

@Service
public class ComputadorFuncionarioService {

	@Autowired
	private ComputadorFuncionarioRepository computadorFuncionarioRepository;
	@Autowired
	private ComputadorService computadorService;
	@Autowired
	private FuncionarioService funcionarioService;

	public ComputadorFuncionario relacionar(Long computadorId, Long funcionarioId) {
		Computador computador = computadorService.apresentar(computadorId);
		Funcionario funcionario = funcionarioService.apresentar(funcionarioId);

		ComputadorFuncionario relacionamento = new ComputadorFuncionario();

		relacionamento.setComputador(computador);
		relacionamento.setFuncionario(funcionario);
		relacionamento.setRecebidoEm(LocalDateTime.now());
		computadorService.alterarStatus(computador, Computador.Status.EM_USO);

		return computadorFuncionarioRepository.save(relacionamento);
	}

	public void desvincular(Long computadorId, Long funcionarioId) {
		Computador computador = computadorService.apresentar(computadorId);
		Funcionario funcionario = funcionarioService.apresentar(funcionarioId);

		List<ComputadorFuncionario> vinculos = computadorFuncionarioRepository
				.findByComputadorAndFuncionario(computador, funcionario);
		for (ComputadorFuncionario computadorFuncionario : vinculos) {
			computadorFuncionario.setDevolvidoEm(LocalDateTime.now());
			computadorService.alterarStatus(computador, Computador.Status.PRA_USO);
			computadorFuncionarioRepository.save(computadorFuncionario);
		}

	}

	public void desvincular(Long computadorId) {
		Computador computador = computadorService.apresentar(computadorId);

		List<ComputadorFuncionario> vinculos = computadorFuncionarioRepository.findByComputador(computador);
		for (ComputadorFuncionario computadorFuncionario : vinculos) {
			computadorFuncionario.setDevolvidoEm(LocalDateTime.now());
			computadorService.alterarStatus(computador, Computador.Status.PRA_USO);
			computadorFuncionarioRepository.save(computadorFuncionario);
		}

	}

	public List<ComputadorFuncionario> historicoComputador(Long computadorId) {
		Computador computador = computadorService.apresentar(computadorId);
		return computadorFuncionarioRepository.findByComputador(computador);
	}

	public List<ComputadorFuncionario> historicoFuncionario(Long funcionarioId){
		Funcionario funcionario = funcionarioService.apresentar(funcionarioId);
		return computadorFuncionarioRepository.findByFuncionario(funcionario);
	}

}