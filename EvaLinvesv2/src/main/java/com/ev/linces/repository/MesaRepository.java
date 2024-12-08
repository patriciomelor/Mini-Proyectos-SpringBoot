package com.ev.linces.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ev.linces.models.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {

    boolean existsByNumeroMesa(int numeroMesa);
}