package com.ev.linces.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ev.linces.models.Reserva;
import com.ev.linces.repository.ReservaRepository;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private MesaService mesaService;

    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public List<Reserva> obtenerTodasReservas() {
        return reservaRepository.findAll();
    }

    public Reserva obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public Reserva actualizarReserva(Reserva reserva) {
        if (!mesaService.verificarDisponibilidad(reserva.getMesa().getId())) {
            // Añadir un mensaje o lógica para manejar el error
            return null;
        }
        return reservaRepository.save(reserva);
    }


    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}