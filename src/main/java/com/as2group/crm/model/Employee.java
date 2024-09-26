package com.as2group.crm.model;

import java.time.LocalDate;
import java.util.Set;

import com.as2group.crm.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table (name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_employee;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "gender")
	private String gender;

	@Column(name = "password")
	private String password;

	@ManyToMany
	@JoinTable(name = "Employee_Role",
	joinColumns = @JoinColumn(name = "id_employee"),
	inverseJoinColumns = @JoinColumn(name = "id_role"))
	private Set<Role> roles;

	@Column(name = "entryDate")
	private LocalDate entryDate;

	@Column(name = "departureDate")
	private LocalDate departureDate;

	@Column(name = "status")
	private Status status;

}