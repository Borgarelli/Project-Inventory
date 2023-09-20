package com.as2group.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.as2group.crm.model.ComputadorFuncionario;
import com.as2group.crm.service.ComputadorFuncionarioService;

@RestController
@RequestMapping("/api")
public class ComputadorFuncionarioController {

	@Autowired
	private ComputadorFuncionarioService computadorFuncionarioService;

	@PostMapping("/computador/{computadorId}/funcionario/{funcionarioId}")
	@ResponseStatus(HttpStatus.CREATED)
	public ComputadorFuncionario create(@PathVariable("computadorId") Long computadorId,
			@PathVariable("funcionarioId") Long funcionarioId) {
		return computadorFuncionarioService.relacionar(computadorId, funcionarioId);
	}

	@DeleteMapping("/computador/{computadorId}/funcionario/{funcionarioId}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("computadorId") Long computadorId,
			@PathVariable("funcionarioId") Long funcionarioId) {
		computadorFuncionarioService.desvinculo(computadorId, funcionarioId);
	}
	
	@GetMapping("/computador/{computadorId}/historico")
	public List<ComputadorFuncionario> historicoComputador(@PathVariable("computadorId") Long computadorId) { 
		return computadorFuncionarioService.historicoComputador(computadorId); 
	}
	
	@GetMapping("/funcionario/{funcionarioId}/historico")
	public List<ComputadorFuncionario> historicoFuncionario(@PathVariable("funcionarioId") Long funcionarioId) { 
		return computadorFuncionarioService.historicoFuncionario(funcionarioId); 
	}

}
