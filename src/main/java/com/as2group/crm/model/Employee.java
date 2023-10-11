package com.as2group.crm.model;

import java.time.LocalDate;
import java.util.Objects;

import com.as2group.crm.enumeration.EmployeeStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_employee;
	

	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	private String telephone;
	private String sex;
	private LocalDate entryDate;
	private LocalDate departureDate;
	private EmployeeStatus status;


	public Long getId() {
		return id_employee;
	}

	public void setId(Long id) {
		this.id_employee = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public EmployeeStatus getStatus() {
		return status;
	}

	public void setStatus(EmployeeStatus status) {
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

	@Override
	public int hashCode() {
		return Objects.hash(id_employee);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(id_employee, other.id_employee);
	}

}
