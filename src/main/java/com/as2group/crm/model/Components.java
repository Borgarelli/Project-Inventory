package com.as2group.crm.model;

import java.util.Objects;

import com.as2group.crm.enumeration.ComponentsStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name = "components")

public class Components {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_component")
	private Long id;

	@Column(nullable = false, unique = true)
	private String patrimony;


	@ManyToOne
	@JoinColumn(name = "id_computer")
	private Computer computer;

	private ComponentsStatus status;
	
//	@Enumerated(EnumType.STRING)
//    @Column(name = "component_type")
//    private ComponentType type;
////	
	private String specifications;

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

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	public ComponentsStatus getStatus() {
		return status;
	}

	public void setStatus(ComponentsStatus status) {
		this.status = status;
	}
	
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}


//	public ComponentType getComponentType() {
//		return type;
//	}
//
//	public void setComponentType(ComponentType componentType) {
//		this.type = componentType;
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Components other = (Components) obj;
		return Objects.equals(id, other.id);
	}



}
