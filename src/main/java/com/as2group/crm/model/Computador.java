package com.as2group.crm.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
//import jakarta.persistence.Transient;

@Entity
@Table (name = "computador")
public class Computador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_computador")
	private Long id;

	@Column(nullable = false, unique = true)
	private String patrimonio;
	private String Sn;

	@OneToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;

	public enum Status {
		EM_USO, PRA_USO, INATIVO;
	}
	
	private String setor;
	private String modelo;
	private String marca;
	private String processador;
	@Column(name = "geracao")
	private String geração;
	private String RAM;
	@Column(name = "placa_de_video")
	private String placaDeVideo;
	private String hd;
	private String ssd;
	@Column(name = "so_atual")
	private String soAtual;
	@Column(name = "so_original")
	private String soOriginal;
	private Status status;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	public String getSn() {
		return Sn;
	}

	public void setSn(String sn) {
		Sn = sn;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getProcessador() {
		return processador;
	}

	public void setProcessador(String processador) {
		this.processador = processador;
	}

	public String getGeração() {
		return geração;
	}

	public void setGeração(String geração) {
		this.geração = geração;
	}

	public String getRAM() {
		return RAM;
	}

	public void setRAM(String rAM) {
		RAM = rAM;
	}

	public String getPlacaDeVideo() {
		return placaDeVideo;
	}

	public void setPlacaDeVideo(String placaDeVideo) {
		this.placaDeVideo = placaDeVideo;
	}

	public String getHd() {
		return hd;
	}

	public void setHd(String hd) {
		this.hd = hd;
	}

	public String getSsd() {
		return ssd;
	}

	public void setSsd(String ssd) {
		this.ssd = ssd;
	}

	public String getSoAtual() {
		return soAtual;
	}

	public void setSoAtual(String soAtual) {
		this.soAtual = soAtual;
	}

	public String getSoOriginal() {
		return soOriginal;
	}

	public void setSoOriginal(String soOriginal) {
		this.soOriginal = soOriginal;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computador other = (Computador) obj;
		return Objects.equals(id, other.id);
	}

}
