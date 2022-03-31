package com.citi.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citi.projeto.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{
	
}
