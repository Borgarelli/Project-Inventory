package com.as2group.crm.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.as2group.crm.model.Computador;
import com.as2group.crm.model.Computador.Status;

public interface ComputadorRepository extends JpaRepository<Computador, Long> {
	
	Optional<Computador> findByPatrimonio(String patrimonio);
	
	List<Computador> findByModeloAndMarca(String modelo, String marca);
	
	Optional<Status> findByStatus(Status status);

	List<Computador> findAllByStatus(@Param("status") Status status);
	
	@Query("SELECT c FROM Computador c WHERE c.patrimonio = :patrimonio")
	Optional<Computador> findByPatrimonioWithQuery(String patrimonio);
	
	@Query("SELECT c FROM Computador c WHERE c.modelo = :modelo AND c.marca = :marca")
	List<Computador> findByModeloAndMarcaWithQuery(String modelo, String marca);
	

}
