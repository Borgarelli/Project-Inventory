package com.as2group.crm.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.as2group.crm.model.ComputadorFuncionario;
import com.as2group.crm.model.Funcionario;
import com.as2group.crm.repository.ComputadorFuncionarioRepository;
import com.as2group.crm.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	ComputadorFuncionarioRepository computadorFuncionarioRepository;

	
	public FuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public List<Funcionario> listar() {
		return funcionarioRepository.findAll();
	}

	public Funcionario apresentar(Long id) {
		Optional<Funcionario> encontrado = funcionarioRepository.findById(id);
		if (encontrado.isPresent()) {
			return encontrado.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
		}
	}

	public Funcionario create(Funcionario funcionario) {
		funcionario.setDataEntrada(LocalDate.now());
		
		return funcionarioRepository.save(funcionario);
	}

	public void delete(Long id) {
		Funcionario funcionario = apresentar(id);
		
		List<ComputadorFuncionario> registrosRelacionados = computadorFuncionarioRepository.findByFuncionario(funcionario);
		computadorFuncionarioRepository.deleteAll(registrosRelacionados);
		
		funcionarioRepository.deleteById(id);
	}
	

	
	public Funcionario edit(Funcionario funcionario, Long id) {
		Funcionario encontrado = apresentar(id);
		encontrado.setEmail(funcionario.getEmail());
		encontrado.setNome(funcionario.getNome());
		encontrado.setSexo(funcionario.getSexo());
		encontrado.setTelefone(funcionario.getTelefone());

		return funcionarioRepository.save(encontrado);
	}

}
