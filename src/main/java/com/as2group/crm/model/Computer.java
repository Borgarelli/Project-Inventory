package com.as2group.crm.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.as2group.crm.enumeration.ComputerStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "computer")

public class Computer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_computer")
	private Long id;

	@Column(nullable = false, unique = true)
	private String patrimony;
	private String sn;

	@OneToOne
	@JoinColumn(name = "id_employee")
	private Employee employee;

	@OneToMany(mappedBy = "computer", fetch = FetchType.EAGER)
	private List<Components> computerWithComponents = new ArrayList<>();
	

 

	private String sector;
	private String model;
	private String brand;
	@Column(name = "so_current")
	private String soCurrent;
	@Column(name = "so_original")
	private String soOriginal;
	private ComputerStatus status;
	
	@Column(name = "entry_date")
	private LocalDate entryDate;
	private LocalDate departureDate;
	private LocalDate modificationDate;



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
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public List<Components> getComputerComponents() {
		return computerWithComponents;
	}

	public void setComputerComponents(List<Components> computerComponents) {
		this.computerWithComponents = computerComponents;
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

	public String getSoCurrent() {
		return soCurrent;
	}

	public void setSoCurrent(String soCurrent) {
		this.soCurrent = soCurrent;
	}

	public String getSoOriginal() {
		return soOriginal;
	}

	public void setSoOriginal(String soOriginal) {
		this.soOriginal = soOriginal;
	}

	public ComputerStatus getStatus() {
		return status;
	}

	public void setStatus(ComputerStatus status) {
		this.status = status;
	}
	
	public LocalDate getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(LocalDate modificationDate) {
		this.modificationDate = modificationDate;
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
