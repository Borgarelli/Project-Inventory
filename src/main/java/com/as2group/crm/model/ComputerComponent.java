package com.as2group.crm.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	
}
