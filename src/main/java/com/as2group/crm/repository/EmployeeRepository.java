package com.as2group.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.as2group.crm.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	Optional<Employee> findByEmail(String email);
	
	@Query("SELECT e FROM Employee e WHERE e.name LIKE %:name%")
	List<Employee> findByName (String name);
	
	@Query("SELECT e FROM Employee e WHERE e.status = com.as2group.crm.enumeration.Status.ATIVO")
	List<Employee> findByStatusActivate();
	
	@Query("SELECT e FROM Employee e WHERE e.status = com.as2group.crm.enumeration.Status.INATIVO")
	List<Employee> findByStatusInactivate();

}
