package com.as2group.crm.controller;

import java.util.List;
import java.util.Optional;

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

import com.as2group.crm.model.Computador;
import com.as2group.crm.model.Computador.Status;
import com.as2group.crm.repository.ComputadorRepository;
import com.as2group.crm.service.ComputadorFuncionarioService;
import com.as2group.crm.service.ComputadorService;

@RestController
@RequestMapping("/api")
public class ComputadorController {

	@Autowired
	ComputadorRepository computadorRepository;

	@Autowired
	ComputadorService computadorService;

	@Autowired
	ComputadorFuncionarioService computadorFuncionarioService;

	@GetMapping("/computador")
	public List<Computador> listar() {
		return computadorService.listar();
	}

	@GetMapping("/computador/status/{status}")
	public List<Computador> listarPorStatus(@PathVariable("status") Status status) {
	    return computadorService.listarPorStatus(status);
	}
	
	@GetMapping("/computador/{id}")
	public Computador apresentar(@PathVariable("id") Long id) {
		return computadorService.apresentar(id);
	}

	@GetMapping("/computador/patrimonio/{patrimonio}")
	public Computador apresentar(@PathVariable("patrimonio") String patrimonio) {
		return computadorService.apresentar(patrimonio);
	}

	@PostMapping("/computador")
	@ResponseStatus(HttpStatus.CREATED)
	public Computador create(@RequestBody Computador computador) {
		return computadorService.create(computador);
	}

	@DeleteMapping("/computador/{id}")
	public void delete(@PathVariable("id") Long id) {
		computadorService.delete(id);
	}

	@PutMapping("/computador/{id}")
	public Computador atualizar(@PathVariable("id") Long id, @RequestBody Computador computador, Long patrimonio,
			String sn, String funcionario, String setor, String modelo, String marca, String processador,
			String geração, String RAM, String placaDeVideo, String hd, String ssd, String soAtual, String soOriginal) {
		return computadorService.edit(computador, id, patrimonio, sn, funcionario, setor, modelo, marca, processador,
				geração, RAM, placaDeVideo, hd, ssd, soAtual, soOriginal);
	}

	@PutMapping("/computador/{id}/inativar")
	public void inativar(@PathVariable("id") Long id) {
		computadorFuncionarioService.desvincular(id);
		computadorService.inativar(id);
	}

	@GetMapping("computador/estoque")
	public Optional<Status> estoque() {
		return computadorService.estoque();
	}
}
