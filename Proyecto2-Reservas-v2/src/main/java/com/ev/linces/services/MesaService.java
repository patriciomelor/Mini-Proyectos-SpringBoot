package com.ev.linces.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ev.linces.models.Mesa;
import com.ev.linces.repository.MesaRepository;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

     public List<Mesa> obtenerMesasDisponibles() {
        return mesaRepository.findAll().stream()
            .filter(Mesa::isDisponible) // Filtra solo las mesas disponibles
            .collect(Collectors.toList());
    }

    public List<Mesa> obtenerMesas() {
        return mesaRepository.findAll();
            
    }


     // Método para crear una nueva mesa
     public Mesa crearMesa(int numeroMesa, int capacidad) {
        // Verificar si ya existe una mesa con ese numero
        if (mesaRepository.existsByNumeroMesa(numeroMesa)) {
            throw new IllegalArgumentException("Ya existe una mesa con ese número " + numeroMesa);
        }

         // Crear y guardar la nueva mesa
         Mesa nuevaMesa = new Mesa(numeroMesa, capacidad, false);  // Asumiendo que la nueva mesa está disponible
         return mesaRepository.save(nuevaMesa);
    }

    public Optional<Mesa>obtenerMesaPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID no puede ser nulo o negativo");
        }
        return mesaRepository.findById(id);
    }

    public Mesa guardarMesa(Mesa mesa) {
        if (mesa == null) {
            throw new IllegalArgumentException("La mesa no puede ser nula");
        }
        return mesaRepository.save(mesa);
    }

    public void deleteById(Long id) {
        if (!mesaRepository.existsById(id)) {
            throw new IllegalArgumentException("La mesa con ID " + id + " no existe");
        }
        mesaRepository.deleteById(id);
    }
    
   public boolean verificarDisponibilidad(Long id) {
    Optional<Mesa> mesa = obtenerMesaPorId(id);
    return mesa.isPresent() && mesa.get().isDisponible() && mesa.get().verificarDisponibilidad(LocalDateTime.now());
}

}