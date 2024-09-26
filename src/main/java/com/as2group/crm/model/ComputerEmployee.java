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
@Table (name = "ComputerEmployee")
public class ComputerEmployee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_comp_empl;

	@ManyToOne
	@JoinColumn(name = "id_computer")
	private Computer computer;

	@ManyToOne
	@JoinColumn(name = "id_employee")
	private Employee employee;

	private LocalDateTime received;
	private LocalDateTime returned;

	public Long getId_comp_empl() {
		return id_comp_empl;
	}
	public void setId_comp_empl(Long id_comp_empl) {
		this.id_comp_empl = id_comp_empl;
	}
	public Computer getComputer() {
		return computer;
	}
	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public LocalDateTime getReceived() {
		return received;
	}
	public void setReceived(LocalDateTime received) {
		this.received = received;
	}
	public LocalDateTime getReturned() {
		return returned;
	}
	public void setReturned(LocalDateTime returned) {
		this.returned = returned;
	}

}
