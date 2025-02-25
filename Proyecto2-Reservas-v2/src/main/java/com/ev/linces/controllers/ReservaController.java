package com.ev.linces.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ev.linces.models.Reserva;
import com.ev.linces.services.ClienteService;
import com.ev.linces.services.MesaService;
import com.ev.linces.services.ReservaService;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private MesaService mesaService;

    @Autowired
    private ClienteService clienteService;

    // Mostrar formulario para crear una nueva reserva
    @GetMapping("/nueva")
    public String formularioReserva(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("mesasDisponibles", mesaService.obtenerMesasDisponibles());
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        return "reservas/reservasForm"; // Vista: reservas/reservasForm.html
    }

    // Guardar una nueva reserva
    @PostMapping("/nueva")
    public String crearReserva(@ModelAttribute Reserva reserva) {
        if (!mesaService.verificarDisponibilidad(reserva.getMesa().getId())) {
            // Agregar mensaje de error de disponibilidad
            return "redirect:/reservas/nueva?error=mesaNoDisponible";
        }
        reservaService.crearReserva(reserva);
        return "redirect:/reservas/lista";
    }

  // Listar todas las reservas
@GetMapping("/lista")
public String listaReservas(Model model) {
    model.addAttribute("reservas", reservaService.obtenerTodasReservas());
    return "reservas/reservasList"; 
}
    // Mostrar formulario para editar una reserva existente
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Reserva reserva = reservaService.obtenerReservaPorId(id);
        if (reserva == null) {
            return "redirect:/reservas/lista"; // Redirigir si no se encuentra
        }
        model.addAttribute("reserva", reserva);
        model.addAttribute("mesasDisponibles", mesaService.obtenerMesasDisponibles());
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        return "reservas/editar"; // Reutiliza reservasForm para ediciÃ³n
    }

    // Actualizar una reserva
    @PostMapping("/editar")
    public String actualizarReserva(@ModelAttribute Reserva reserva) {
        reservaService.actualizarReserva(reserva);
        return "redirect:/reservas/lista";
    }

    // Eliminar una reserva
    @GetMapping("/eliminar/{id}")
    public String eliminarReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
        return "redirect:/reservas/lista";
    }
}