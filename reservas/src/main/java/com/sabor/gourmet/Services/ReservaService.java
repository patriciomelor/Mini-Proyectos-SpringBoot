package com.sabor.gourmet.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sabor.gourmet.Model.Mesa;
import com.sabor.gourmet.Model.Reserva;
import com.sabor.gourmet.Repository.MesaRepository;
import com.sabor.gourmet.Repository.ReservaRepository;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private MesaRepository mesaRepository;

    public Reserva crearReserva(Reserva reserva) {
        Mesa mesa = mesaRepository.findById(reserva.getMesa().getId())
                .orElseThrow(() -> new MesaNoEncontradaException("Mesa no encontrada"));
        if (!mesa.isDisponible()) {
            throw new MesaNoDisponibleException("Mesa no disponible");
        }
        mesa.setDisponible(false);
        mesaRepository.save(mesa); 
        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public void cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada")); 
        reserva.setActiva(false);
        reserva.getMesa().setDisponible(true);
        reservaRepository.save(reserva); // La mesa se guarda en cascada.
    }
}

// Excepciones personalizadas
class MesaNoEncontradaException extends RuntimeException {
    public MesaNoEncontradaException(String message) {
        super(message);
    }
}

class MesaNoDisponibleException extends RuntimeException {
    public MesaNoDisponibleException(String message) {
        super(message);
    }
}