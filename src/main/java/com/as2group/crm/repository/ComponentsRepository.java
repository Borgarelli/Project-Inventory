package com.as2group.crm.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.as2group.crm.enumeration.ComponentType;
import com.as2group.crm.model.Components;
import com.as2group.crm.model.Components.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;

public interface ComponentsRepository extends JpaRepository<Components, Long> {

	Optional<Components> findByPatrimony(String patrimony);

	List<Components> findByModelAndBrand(String model, String brand);

//	Optional<Components> findBySn(String sn);

	Optional<Status> findByStatus(Status status);
	
	Optional<ComponentType> findByType(ComponentType componentType);

	@Transactional
	void deleteByPatrimony(String patrimony);

	List<Components> findAllByStatus(@Param("status") Status status);
	
	List<Components> findAllByType(@Param("type") ComponentType componentType);

	@Query("SELECT c FROM Computer c WHERE c.patrimony = :patrimony")
	Optional<Components> findByPatrimonyWithQuery(String patrimony);

	@Query("SELECT c FROM Computer c WHERE c.model = :model AND c.brand = :brand")
	List<Components> findByModelAndbrandWithQuery(String model, String brand);

}