package com.sabor.gourmet.Controller;

import com.sabor.gourmet.Model.Reserva;
import com.sabor.gourmet.Model.Mesa;
import com.sabor.gourmet.Services.ReservaService;
import com.sabor.gourmet.Repository.MesaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que maneja las rutas y acciones para las Reservas.
 */
@Controller
@RequestMapping("/Reservas")
public class ReservaController {

    @Autowired
    private MesaRepository MesaRepository;

    @Autowired
    private ReservaService ReservaService;

    /**
     * Muestra la lista de Reservas.
     * @param model Modelo de la vista.
     * @return Vista de lista de Reservas.
     */
    @GetMapping
    public String listarReservas(Model model) {
        List<Reserva> Reservas = ReservaService.listarReservas();
        model.addAttribute("Reservas", Reservas);
        return "Reservas/listar";
    }

    /**
     * Muestra el formulario para crear una nueva Reserva.
     * @param model Modelo de la vista.
     * @return Vista del formulario de creación.
     */
    @GetMapping("/crear")
    public String mostrarFormularioReserva(Model model) {
        List<Mesa> MesasDisponibles = MesaRepository.findByDisponible(true);
        model.addAttribute("Mesas", MesasDisponibles);
        model.addAttribute("Reserva", new Reserva());
        return "Reservas/crear";
    }

    /**
     * Crea una nueva Reserva en el sistema.
     * @param Reserva Objeto Reserva con los datos ingresados.
     * @param MesaId ID de la Mesa seleccionada.
     * @return Redirección a la lista de Reservas.
     */
    @PostMapping("/crear")
    public String crearReserva(@ModelAttribute Reserva Reserva, @RequestParam Long MesaId) {
        Mesa Mesa = MesaRepository.findById(MesaId)
            .orElseThrow(() -> new RuntimeException("Mesa no encontrada con ID " + MesaId));
        Reserva.setMesa(Mesa);
        ReservaService.crearReserva(Reserva);
        return "redirect:/Reservas";
    }

    /**
     * Cancela una Reserva específica.
     * @param id ID de la Reserva a cancelar.
     * @return Redirección a la lista de Reservas.
     */
    @PostMapping("/cancelar/{id}")
    public String cancelarReserva(@PathVariable Long id) {
        ReservaService.cancelarReserva(id);
        return "redirect:/Reservas";
    }
}
