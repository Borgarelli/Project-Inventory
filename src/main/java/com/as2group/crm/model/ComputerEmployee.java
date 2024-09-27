package com.as2group.crm.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

}
