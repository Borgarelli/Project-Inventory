package com.as2group.crm.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.as2group.crm.enumeration.ComponentsStatus;
import com.as2group.crm.model.Components;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;

public interface ComponentsRepository extends JpaRepository<Components, Long> {

	Optional<Components> findByPatrimony(String patrimony);

//	List<Components> findByModelAndBrand(String model, String brand);

//	Optional<Components> findBySn(String sn);

	Optional<ComponentsStatus> findByStatus(ComponentsStatus status);
	
//	Optional<ComponentType> findByType(ComponentType componentType);

	@Transactional
	void deleteByPatrimony(String patrimony);

	List<Components> findAllByStatus(@Param("status") ComponentsStatus status);
	
//	List<Components> findAllByType(@Param("type") ComponentType componentType);


}