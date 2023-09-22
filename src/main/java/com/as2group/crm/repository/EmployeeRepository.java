package com.as2group.crm.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.as2group.crm.model.Employee;



public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findByEmail(String email);
	
	@Query("SELECT e FROM Employee e WHERE e.status = com.as2group.crm.model.Employee$Status.ATIVO")
	List<Employee> findByStatusActivate();


}
