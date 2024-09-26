package com.as2group.crm.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.as2group.crm.enums.Status;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

	@Column(name = "sector")
	private String sector;

	@Column(name = "model")
	private String model;

	@Column(name = "brand")
	private String brand;

	@Column(name = "so_current")
	private String soCurrent;

	@Column(name = "so_original")
	private String soOriginal;

	@Column(name = "status")
	private Status status;
	
	@Column(name = "entry_date")
	private LocalDate entryDate;

	@Column(name = "departureDate")
	private LocalDate departureDate;

	@Column(name = "modifications")
	private LocalDate modificationDate;

}
