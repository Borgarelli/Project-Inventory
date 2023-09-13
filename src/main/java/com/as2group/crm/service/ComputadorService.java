package com.as2group.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.as2group.crm.model.Computador;
import com.as2group.crm.model.Computador.Status;
import com.as2group.crm.repository.ComputadorRepository;

@Service
public class ComputadorService {

	ComputadorRepository computadorRepository;

	// Constructor
	public ComputadorService(ComputadorRepository computadorRepository) {
		this.computadorRepository = computadorRepository;

		Computador computador = new Computador();
		computador.setPatrimonio("NTK191212");
		computador.setSn("14719733426");
		computador.setSetor("TI");
		computador.setModelo("Inspiron 14R 5437");
		computador.setMarca("Dell");
		computador.setProcessador("i7");
		computador.setGeração("4º Geração");
		computador.setRAM("16Gb");
		computador.setPlacaDeVideo(null);
		computador.setHd(null);
		computador.setSsd("256gb");
		computador.setSoAtual("Ubuntu 22.04.2 LTS");
		computador.setSoOriginal("Windows 10");
		computador.setStatus(Computador.Status.PRA_USO);

		this.computadorRepository.save(computador);
	}

	public void alterarStatus(Computador computador, Computador.Status status) {
		computador.setStatus(status);
		this.computadorRepository.save(computador);
	}

	// GetAll
	public List<Computador> listar() {
		return computadorRepository.findAll();
	}

	// GetById
	public Computador apresentar(Long id) {
		Optional<Computador> encontrado = computadorRepository.findById(id);
		if (encontrado.isPresent()) {
			return encontrado.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Computer not found");
		}
	}

	// GetByPatrimonio
	public Computador apresentar(String patrimonio) {
		Optional<Computador> encontrado = computadorRepository.findByPatrimonio(patrimonio);
		if (encontrado.isPresent()) {
			return encontrado.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Computer not found");
		}
	}

	// Post
	public Computador create(Computador computador) {
		Optional<Computador> encontrado = computadorRepository.findByPatrimonio(computador.getPatrimonio());
		if (encontrado.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patrimonio do computador já está em uso.");

		computador.setStatus(Computador.Status.PRA_USO);
		return computadorRepository.save(computador);
	}

	// Delete
	public void delete(Long id) {
		computadorRepository.deleteById(id);
	}

	// Put
	public Computador edit(Computador computador, Long id, Long patrimonio, String sn, String funcionario, String setor,
			String modelo, String marca, String processador, String geração, String RAM, String placaDeVideo, String hd,
			String ssd, String soAtual, String soOriginal) {
		Computador encontrado = apresentar(id);
		encontrado.setPatrimonio(computador.getPatrimonio());
		encontrado.setSn(computador.getSn());
		encontrado.setFuncionario(computador.getFuncionario());
		encontrado.setSetor(computador.getSetor());
		encontrado.setModelo(computador.getModelo());
		encontrado.setMarca(computador.getMarca());
		encontrado.setProcessador(computador.getProcessador());
		encontrado.setGeração(computador.getGeração());
		encontrado.setRAM(computador.getRAM());
		encontrado.setPlacaDeVideo(computador.getPlacaDeVideo());
		encontrado.setHd(computador.getHd());
		encontrado.setSsd(computador.getSsd());
		encontrado.setSoAtual(computador.getSoAtual());
		encontrado.setSoOriginal(computador.getSoOriginal());

		return computadorRepository.save(encontrado);
	}

	// PutInativar
	public void inativar(Long id) {
		Computador computador = apresentar(id);
		alterarStatus(computador, Computador.Status.INATIVO);

	}

	//GetEstoque
	public Optional<Status> estoque() {
		Optional<Status> encontrado = computadorRepository.findByStatus(Status.PRA_USO);
		if (encontrado.isPresent()) {
			return Optional.of(encontrado.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "estoque vazio");
		}
	
	}
}
