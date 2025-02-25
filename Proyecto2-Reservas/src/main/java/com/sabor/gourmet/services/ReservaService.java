package com.sabor.gourmet.Services;

import com.sabor.gourmet.Model.Reserva;
import com.sabor.gourmet.Model.Mesa;
import com.sabor.gourmet.Repository.MesaRepository;
import com.sabor.gourmet.Repository.ReservaRepository;
import com.sabor.gourmet.exceptions.MesaNoDisponibleException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio que gestiona la lógica de negocio para las Reservas.
 */
@Service
public class ReservaService {

    @Autowired
    private MesaRepository MesaRepository;

    @Autowired
    private ReservaRepository ReservaRepository;

    /**
     * Crea una nueva Reserva en el sistema.
     * @param Reserva Objeto Reserva a crear.
     * @return Objeto Reserva creado.
     */
    public Reserva crearReserva(Reserva Reserva) {
        if (Reserva.getMesa() == null) {
            throw new RuntimeException("No se ha asociado una Mesa a la Reserva");
        }
        Mesa Mesa = Reserva.getMesa();
        if (!Mesa.isDisponible()) {
            throw new MesaNoDisponibleException("La Mesa con ID " + Mesa.getId() + " no está disponible");
        }
        Mesa.setDisponible(false);
        MesaRepository.save(Mesa);
        return ReservaRepository.save(Reserva);
    }

    /**
     * Lista todas las Reservas del sistema.
     * @return Lista de Reservas.
     */
    public List<Reserva> listarReservas() {
        return ReservaRepository.findAll();
    }

    /**
     * Cancela una Reserva y libera la Mesa asociada.
     * @param id ID de la Reserva a cancelar.
     */
    public void cancelarReserva(Long id) {
        Reserva Reserva = ReservaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID " + id));
        Mesa Mesa = Reserva.getMesa();
        Mesa.setDisponible(true); // Liberar la Mesa
        Reserva.setActiva(false); // Cancelar la Reserva
        ReservaRepository.save(Reserva);
        MesaRepository.save(Mesa); // Guardar cambios en la Mesa
    }
}
