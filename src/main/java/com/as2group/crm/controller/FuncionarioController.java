package com.as2group.crm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.as2group.crm.model.Funcionario;
import com.as2group.crm.repository.FuncionarioRepository;
import com.as2group.crm.service.FuncionarioService;

@RestController
@RequestMapping("/api")
public class FuncionarioController {

	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Autowired
	FuncionarioService funcioarioService;

	@GetMapping("/funcionario")
	public List<Funcionario> listar() {
		return funcioarioService.listar();

	}

	@GetMapping("/funcionario/{id}")

	public Funcionario apresentar(@PathVariable("id") Long id) {
		return funcioarioService.apresentar(id);
	}

	@PostMapping("/funcionario")
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario create(@RequestBody Funcionario funcionario) {

		return funcioarioService.create(funcionario);
	}

	@DeleteMapping("/funcionario/{id}")
	public void delete(@PathVariable("id") Long id) {
		funcioarioService.delete(id);
	}

	@PutMapping("/funcionario/{id}")
	public Funcionario atualizar(@PathVariable("id") Long id, @RequestBody Funcionario funcionario, String email, 
			String nome, String sexo, String telefone) {
		return funcioarioService.edit(funcionario, id);
	}

}
