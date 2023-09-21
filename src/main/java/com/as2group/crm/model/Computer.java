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
@Table (name = "computer")
public class Computer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_computer")
	private Long id;

	@Column(nullable = false, unique = true)
	private String patrimony;
	private String Sn;

	@OneToOne
	@JoinColumn(name = "id_employee")
	private Employee employee;

	public enum Status {
		EM_USO, PRA_USO, INATIVO;
	}

	private String sector;
	private String model;
	private String brand;
	private String processor;
	@Column(name = "geracao")
	private String generation;
	private String ram;
	@Column(name = "graphics_card")
	private String graphicsCard;
	private String hd;
	private String ssd;
	@Column(name = "so_current")
	private String soCorrent;
	@Column(name = "so_original")
	private String soOriginal;
	private Status status;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getPatrimony() {
		return patrimony;
	}



	public void setPatrimony(String patrimony) {
		this.patrimony = patrimony;
	}



	public String getSn() {
		return Sn;
	}



	public void setSn(String sn) {
		Sn = sn;
	}



	public Employee getEmployee() {
		return employee;
	}



	public void setEmployee(Employee employee) {
		this.employee = employee;
	}



	public String getSector() {
		return sector;
	}



	public void setSector(String sector) {
		this.sector = sector;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getProcessor() {
		return processor;
	}



	public void setProcessor(String processor) {
		this.processor = processor;
	}



	public String getGeneration() {
		return generation;
	}



	public void setGeneration(String generation) {
		this.generation = generation;
	}



	public String getRam() {
		return ram;
	}



	public void setRam(String ram) {
		this.ram = ram;
	}



	public String getGraphicsCard() {
		return graphicsCard;
	}



	public void setGraphicsCard(String graphicsCard) {
		this.graphicsCard = graphicsCard;
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



	public String getSoCorrent() {
		return soCorrent;
	}



	public void setSoCorrent(String soCorrent) {
		this.soCorrent = soCorrent;
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
		Computer other = (Computer) obj;
		return Objects.equals(id, other.id);
	}

}
