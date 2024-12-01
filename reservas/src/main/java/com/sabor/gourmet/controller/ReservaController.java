package com.sabor.gourmet.controller;

import com.sabor.gourmet.model.reserva;
import com.sabor.gourmet.model.cliente;
import com.sabor.gourmet.model.mesa;
import com.sabor.gourmet.repository.ClienteRepository;
import com.sabor.gourmet.repository.MesaRepository;
import com.sabor.gourmet.repository.ReservaRepository;
import com.sabor.gourmet.services.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ReservaRepository reservaRepository;

    // Listar todas las reservas
    @GetMapping
    public String listarReservas(Model model) {
        model.addAttribute("reservas", reservaRepository.findAll());
        return "reservas/listar";
    }

    // Mostrar formulario para crear una reserva
    @GetMapping("/crear")
    public String mostrarFormularioReserva(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("mesas", mesaRepository.findByDisponible(true));
        model.addAttribute("reserva", new reserva());
        return "reservas/crear";
    }
    

    // Crear una nueva reserva
    @PostMapping("/crear")
    public String crearReserva(@RequestParam(required = true) Long clienteId, @RequestParam(required = true) Long mesaId) {
        // Validar ID de cliente
        if (clienteId == null || clienteId <= 0) {
            throw new IllegalArgumentException("ID de cliente no válido.");
        }
    
        // Validar ID de mesa
        if (mesaId == null || mesaId <= 0) {
            throw new IllegalArgumentException("ID de mesa no válido.");
        }
    
        // Buscar cliente
        cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));
    
        // Buscar mesa
        mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada con ID: " + mesaId));
    
        // Crear reserva
        reserva reserva = new reserva();
        reserva.setCliente(cliente);
        reserva.setMesa(mesa);
        reserva.setActiva(true);
    
        // Guardar reserva
        reservaRepository.save(reserva);
    
        return "redirect:/reservas";
    }
    
    // Cancelar una reserva
    @PostMapping("/cancelar/{id}")
    public String cancelarReserva(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        return "redirect:/reservas";
    }
}
