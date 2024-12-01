package com.sabor.gourmet.controller;

import com.sabor.gourmet.model.reserva;
import com.sabor.gourmet.model.cliente;
import com.sabor.gourmet.model.mesa;
import com.sabor.gourmet.repository.ClienteRepository;
import com.sabor.gourmet.repository.MesaRepository;
import com.sabor.gourmet.repository.ReservaRepository;
import com.sabor.gourmet.services.ReservaService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.format.DateTimeFormatter;

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

    @GetMapping
    public String listarReservas(Model model) {
        List<reserva> reservas = reservaRepository.findAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        reservas.forEach(reserva -> {
            if (reserva.getFecha() != null) {
                reserva.setFechaFormatted(reserva.getFecha().format(formatter));
            }
        });

        model.addAttribute("reservas", reservas);
        return "reservas/listar";
    }

    @GetMapping("/crear")
    public String mostrarFormularioReserva(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("mesas", mesaRepository.findByDisponible(true));
        model.addAttribute("reserva", new reserva());
        return "reservas/crear";
    }

    @PostMapping("/crear")
    public String crearReserva(@RequestParam(required = true) Long clienteId, @RequestParam(required = true) Long mesaId) {
        if (clienteId == null || clienteId <= 0) {
            throw new IllegalArgumentException("ID de cliente no válido.");
        }
        if (mesaId == null || mesaId <= 0) {
            throw new IllegalArgumentException("ID de mesa no válido.");
        }

        cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));
        mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada con ID: " + mesaId));

        reserva reserva = new reserva();
        reserva.setCliente(cliente);
        reserva.setMesa(mesa);
        reserva.setActiva(true);

        reservaRepository.save(reserva);

        return "redirect:/reservas";
    }

    @PostMapping("/cancelar/{id}")
    public String cancelarReserva(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        return "redirect:/reservas";
    }
}
