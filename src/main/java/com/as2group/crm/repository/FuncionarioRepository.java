package com.as2group.crm.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.as2group.crm.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
