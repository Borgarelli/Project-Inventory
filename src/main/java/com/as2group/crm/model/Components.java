package com.as2group.crm.model;

import java.util.Objects;
import com.as2group.crm.dto.ComponentTypeDTO;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

//@Entity
//@Table(name = "components")
public class Components {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_components")
	private Long id;

	@Column(nullable = false, unique = true)
	private String patrimony;

	@OneToMany
	@JoinColumn(name = "id_computer")
	private Computer computer;

	public enum Status {
		EM_USO, PRA_USO, INATIVO;
	}

	private Status status;
	private ComponentTypeDTO componentTypeDTO;

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ComponentTypeDTO getComponentTypeDTO() {
		return componentTypeDTO;
	}

	public void setComponentTypeDTO(ComponentTypeDTO componentTypeDTO) {
		this.componentTypeDTO = componentTypeDTO;
	}

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
