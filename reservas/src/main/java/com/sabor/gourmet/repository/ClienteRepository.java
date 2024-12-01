package com.sabor.gourmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sabor.gourmet.model.cliente;

public interface ClienteRepository extends JpaRepository<cliente, Long> {}
