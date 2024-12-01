package com.sabor.gourmet.services;

import com.sabor.gourmet.model.Mesa;
import com.sabor.gourmet.repository.MesaRepository;
import com.sabor.gourmet.repository.ReservaRepository;
import com.sabor.gourmet.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva crearReserva(Reserva reserva) {
        Mesa mesa = mesaRepository.findById(reserva.getMesa().getId())
                                   .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
        if (!mesa.isDisponible()) throw new RuntimeException("Mesa no disponible");
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
        reservaRepository.save(reserva);
        mesaRepository.save(reserva.getMesa());
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
