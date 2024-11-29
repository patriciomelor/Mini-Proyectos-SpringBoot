package com.sabor.gourmet.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sabor.gourmet.Model.Mesa;
import com.sabor.gourmet.Model.Reserva;
import com.sabor.gourmet.Repository.MesaRepository;
import com.sabor.gourmet.Repository.ReservaRepository;

import java.util.List;

@Service
public class ReservaService<mesaRepository> {
    @Autowired
    private ReservaRepository ReservaRepository;

    @Autowired
    private MesaRepository MesaRepository;

    public Reserva crearReserva(Reserva reserva) {
        Mesa mesa = MesaRepository.findById(reserva.getMesa().getId())
                                   .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
        if (!mesa.isDisponible()) throw new RuntimeException("Mesa no disponible");
        mesa.setDisponible(false);
        MesaRepository.save(mesa);
        return ReservaRepository.save(reserva);
    }

    public List<Reserva> listarReservas() {
        return ReservaRepository.findAll();
    }

    public void cancelarReserva(Long id) {
        Reserva reserva = ReservaRepository.findById(id)
                                           .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reserva.setActiva(false);
        reserva.getMesa().setDisponible(true);
        ReservaRepository.save(reserva);
        MesaRepository.save(reserva.getMesa());
    }
}
