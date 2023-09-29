package com.as2group.crm.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.as2group.crm.model.Computer;
import com.as2group.crm.model.Computer.Status;


public interface ComputerRepository extends JpaRepository<Computer, Long> {
	
	Optional<Computer> findByPatrimony(String patrimony);
	
	Optional<Computer> findBySn(String sn);
	
	List<Computer> findByModelAndBrand(String model, String brand);
	
	Optional<Status> findByStatus(Status status);

	List<Computer> findAllByStatus(@Param("status") Status status);
	
	
	@Query("SELECT c FROM Computer c WHERE c.patrimony = :patrimony")
	Optional<Computer> findByPatrimonyWithQuery(String patrimony);
	
	@Query("SELECT c FROM Computer c WHERE c.model = :model AND c.brand = :brand")
	List<Computer> findByModelAndbrandWithQuery(String model, String brand);
	
	@Query("SELECT c FROM Computer c WHERE c.status = com.as2group.crm.model.Computer$Status.PRA_USO ORDER BY c.id DESC")
	List<Computer> findByStatusActivate();

}
