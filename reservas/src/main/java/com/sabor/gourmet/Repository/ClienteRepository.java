package com.sabor.gourmet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sabor.gourmet.Model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
