package com.sabor.gourmet.repository;

import com.sabor.gourmet.model.reserva; // Solo importa Reserva, ya que es necesaria para JpaRepository

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<reserva, Long> {}
