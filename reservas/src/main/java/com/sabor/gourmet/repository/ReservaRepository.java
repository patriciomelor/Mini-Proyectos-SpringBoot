package com.sabor.gourmet.repository;

import com.sabor.gourmet.model.Reserva; // Solo importa Reserva, ya que es necesaria para JpaRepository

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {}
