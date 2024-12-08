package com.ev.linces.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ev.linces.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}