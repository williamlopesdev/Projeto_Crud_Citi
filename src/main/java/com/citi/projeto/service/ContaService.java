package com.citi.projeto.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;


import com.citi.projeto.model.Conta;
import com.citi.projeto.service.exception.RecursoNaoEncontradoException;

import com.citi.projeto.repository.ContaRepository;


@Service
public class ContaService {
	private ContaRepository contaRepository;
	
	
	public ContaService(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}
	
	public Page<Conta> findAll(Pageable pageable){
		Page<Conta> list = contaRepository.findAll(pageable);
		
		return list;
	}
	
	public Conta getByID(Long id) {
		Optional<Conta> conta = contaRepository.findById(id);
		Conta p = conta.orElseThrow(() -> new RuntimeException("Conta não encontrada"));
		return p;
	}

	public Conta create (Conta conta) {
		
		Conta p = new Conta();
		p.setNameOwner(conta.getNameOwner());
		p.setAgencyCode(conta.getAgencyCode());
		p.setNumberAccount(conta.getNumberAccount());
		p.setDigitVerification(conta.getDigitVerification());
		p.setRegisterId(conta.getDigitVerification());
		contaRepository.save(p);
		
		return p;
	}
	
	@Transactional
	public Conta update(Conta conta, Long id) {
		try {
			Optional<Conta> p = contaRepository.findById(id);
			p.get().setNameOwner(conta.getNameOwner());
			p.get().setAgencyCode(conta.getAgencyCode());
			p.get().setNumberAccount(conta.getNumberAccount());
			p.get().setDigitVerification(conta.getDigitVerification());
			p.get().setRegisterId(conta.getDigitVerification());
			
			contaRepository.save(p.get());
			
			return p.get();
		
			}catch(EntityNotFoundException e) {
				throw new RecursoNaoEncontradoException("Id: "+id+" não foi encontrado");
			}catch(NoSuchElementException e) {
				throw new RecursoNaoEncontradoException("Id: "+id+" não foi encontrado");
			}
	
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			
			contaRepository.deleteById(id);
			
			
		}catch(EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException("Id: "+id+" não foi encontrado");
		}
	}
	
	
}
