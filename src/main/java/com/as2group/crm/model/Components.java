package com.as2group.crm.model;

import com.as2group.crm.enumeration.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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
@Table(name = "components")

public class Components {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_component")
	private Long id;

	@Column(nullable = false, unique = true)
	private String patrimony;
	private String sn;

	@ManyToOne
	@JoinColumn(name = "id_computer")
	private Computer computer;

	@Column(name = "status")
	private Status status;
	
	@Column(name = "specifications")
	private String specifications;


}
