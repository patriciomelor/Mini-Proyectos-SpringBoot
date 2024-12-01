package com.sabor.gourmet.controller;

import com.sabor.gourmet.model.Cliente;
import com.sabor.gourmet.model.Reserva;
import com.sabor.gourmet.model.Mesa;
import com.sabor.gourmet.repository.ClienteRepository;
import com.sabor.gourmet.repository.MesaRepository;
import com.sabor.gourmet.repository.ReservaRepository;
import com.sabor.gourmet.services.ReservaService;

import java.time.format.DateTimeFormatter;
import java.util.List;

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
        List<Reserva> reservas = reservaRepository.findAll();

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
        model.addAttribute("reserva", new Reserva());
        return "reservas/crear";
    }

    @PostMapping("/crear")
    public String crearReserva(@RequestParam(required = true) Long clienteId, @RequestParam(required = true) Long mesaId) {
        if (clienteId == null || clienteId <= 0) {
            throw new IllegalArgumentException("ID de Cliente no válido.");
        }
        if (mesaId == null || mesaId <= 0) {
            throw new IllegalArgumentException("ID de mesa no válido.");
        }

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada con ID: " + mesaId));

        Reserva reserva = new Reserva();
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