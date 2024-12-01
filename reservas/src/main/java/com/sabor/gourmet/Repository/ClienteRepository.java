package com.sabor.gourmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sabor.gourmet.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
