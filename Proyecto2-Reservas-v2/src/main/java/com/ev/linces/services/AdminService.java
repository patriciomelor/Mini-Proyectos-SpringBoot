package com.ev.linces.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ev.linces.models.Reserva;
import com.ev.linces.repository.ReservaRepository;

@Service
public class AdminService {

     @Autowired
    private ReservaRepository reservaRepository;

    // Obtener todas las reservas
    public List<Reserva> obtenerTodasReservas() {
        return reservaRepository.findAll();
    }

    // Obtener una reserva por ID
    public Reserva obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    // Actualizar una reserva existente
    public Reserva actualizarReserva(Reserva reserva) {
        if (!reservaRepository.existsById(reserva.getId())) {
            throw new IllegalArgumentException("Reserva con ID " + reserva.getId() + " no existe");
        }
        return reservaRepository.save(reserva);
    }

    // Eliminar una reserva por ID
    public void eliminarReserva(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new IllegalArgumentException("Reserva con ID " + id + " no existe");
        }
        reservaRepository.deleteById(id);
    }
}