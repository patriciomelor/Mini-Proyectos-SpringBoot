package com.ev.linces.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ev.linces.models.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}