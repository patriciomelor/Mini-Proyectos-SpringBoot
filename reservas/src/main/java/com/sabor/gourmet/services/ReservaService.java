package com.sabor.gourmet.services;

import com.sabor.gourmet.model.Reserva;
import com.sabor.gourmet.model.Mesa;
import com.sabor.gourmet.repository.MesaRepository;
import com.sabor.gourmet.repository.ReservaRepository;
import com.sabor.gourmet.exceptions.MesaNoDisponibleException;

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
        if (reserva.getMesa() == null) {
            throw new RuntimeException("No se ha asociado una mesa a la Reserva");
        }
        Mesa mesa = reserva.getMesa();
        if (!mesa.isDisponible()) {
            throw new MesaNoDisponibleException("La mesa con ID " + mesa.getId() + " no está disponible");
        }
        mesa.setDisponible(false);
        mesaRepository.save(mesa);
        return reservaRepository.save(reserva);
    }
    
    

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll(); // Retornar todas las reservas
    }

    public void cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID " + id));
        Mesa mesa = reserva.getMesa();
        mesa.setDisponible(true); // Liberar la mesa
        reserva.setActiva(false); // Cancelar la Reserva
        reservaRepository.save(reserva);
        mesaRepository.save(mesa); // Guardar cambios en la mesa
    }
    
}
