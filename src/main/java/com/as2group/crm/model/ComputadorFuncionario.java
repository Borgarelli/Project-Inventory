package com.as2group.crm.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "ComputadorFuncionario")
public class ComputadorFuncionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "computador_id")
	private Computador computador;

	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	private LocalDateTime recebidoEm;
	private LocalDateTime devolvidoEm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Computador getComputador() {
		return computador;
	}

	public void setComputador(Computador computador) {
		this.computador = computador;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public LocalDateTime getRecebidoEm() {
		return recebidoEm;
	}

	public void setRecebidoEm(LocalDateTime recebidoEm) {
		this.recebidoEm = recebidoEm;
	}

	public LocalDateTime getDevolvidoEm() {
		return devolvidoEm;
	}

	public void setDevolvidoEm(LocalDateTime devolvidoEm) {
		this.devolvidoEm = devolvidoEm;
	}

}
