package com.sabor.gourmet.repository;


import com.sabor.gourmet.model.cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<cliente, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
