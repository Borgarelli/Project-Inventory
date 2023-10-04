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
@Table (name = "ComputerComponents")
public class ComputerComponent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_comp_compo;
	
	@ManyToOne
	@JoinColumn(name = "id_computer")
	private Computer computer;

	@ManyToOne
	@JoinColumn(name = "id_component")
	private Components component;
	
	private LocalDateTime received;
	private LocalDateTime returned;
	
	public Long getId_comp_compo() {
		return id_comp_compo;
	}
	
	public void setId_comp_compo(Long id_comp_compo) {
		this.id_comp_compo = id_comp_compo;
	}
	
	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
	public Components getComponent() {
		return component;
	}

	public void setComponent(Components component) {
		this.component = component;
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
