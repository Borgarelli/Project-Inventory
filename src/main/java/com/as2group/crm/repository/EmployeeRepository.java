package com.as2group.crm.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.as2group.crm.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
