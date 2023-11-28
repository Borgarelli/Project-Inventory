package com.as2group.crm.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "role")
public class Role {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_role;

    private String level;

    @ManyToMany(mappedBy = "roles")
    private Set<Employee> employees;

    

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Long getId() {
		return id_role;
	}

	public void setId(Long id) {
		this.id_role = id;
    }
}
