package com.ev.linces.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ev.linces.models.Cliente;
import com.ev.linces.models.Mesa;
import com.ev.linces.models.Reserva;
import com.ev.linces.services.ClienteService;
import com.ev.linces.services.MesaService;
import com.ev.linces.services.ReservaService;

@Controller
@RequestMapping("/admins") // Base de las rutas para administradores
public class AdminController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MesaService mesaService;

    @Autowired
    private ReservaService reservaService;

    // --------- Rutas para CLIENTES ---------

    @GetMapping("/clientes/list")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        return "admins/clientes/clientesList"; // Vista para listar clientes
    }

    @GetMapping("/clientes/form")
    public String formularioNuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "admins/clientes/clientesForm"; // Vista para crear un nuevo cliente
    }

    @PostMapping("/clientes/form")
    public String crearCliente(@ModelAttribute Cliente cliente) {
        clienteService.crearCliente(cliente);
        return "redirect:/admins/clientes/list"; // Redirige a la lista de clientes
    }

    @GetMapping("/clientes/editar/{id}")
    public String formularioEditarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.obtenerClientePorId(id).orElse(null);
        if (cliente == null) {
            return "redirect:/admins/clientes/list";
        }
        model.addAttribute("cliente", cliente);
        return "admins/clientes/clientesForm"; // Vista para editar un cliente
    }

    @PostMapping("/clientes/editar/{id}")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute Cliente clienteDetalles) {
        clienteService.obtenerClientePorId(id).ifPresent(cliente -> {
            cliente.setNombreCliente(clienteDetalles.getNombreCliente());
            cliente.setTelefonoCliente(clienteDetalles.getTelefonoCliente());
            cliente.setEmailCliente(clienteDetalles.getEmailCliente());
            clienteService.crearCliente(cliente);
        });
        return "redirect:/admins/clientes/list"; // Redirige a la lista después de actualizar
    }

    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/admins/clientes/list"; // Redirige a la lista después de eliminar
    }

    // --------- Rutas para MESAS ---------

    @GetMapping("/mesas")
    public String getAllMesas(Model model) {
        model.addAttribute("mesas", mesaService.obtenerMesas()); // Obtener todas las mesas
        return "admins/mesas/mesasList"; // Vista para listar mesas
    }

    @GetMapping("/mesas/nueva")
    public String showCreateMesaForm(Model model) {
        model.addAttribute("mesa", new Mesa());
        return "admins/mesas/mesasForm"; // Vista para crear una nueva mesa
    }

    @PostMapping("/mesas/nueva")
    public String createMesa(@ModelAttribute Mesa mesa) {
        mesaService.guardarMesa(mesa);
        return "redirect:/admins/mesas"; // Redirige a la lista de mesas
    }

    @GetMapping("/mesas/editar/{id}")
    public String showEditMesaForm(@PathVariable Long id, Model model) {
        Mesa mesa = mesaService.obtenerMesaPorId(id).orElse(null);
        if (mesa != null) {
            model.addAttribute("mesa", mesa);
            return "admins/mesas/mesasForm"; // Reutiliza el formulario para editar
        } else {
            return "redirect:/admins/mesas"; // Redirige si no se encuentra la mesa
        }
    }

    @PostMapping("/mesas/editar/{id}")
    public String updateMesa(@PathVariable Long id, @ModelAttribute Mesa mesaDetails) {
        return mesaService.obtenerMesaPorId(id)
                .map(mesa -> {
                    mesa.setNumeroMesa(mesaDetails.getNumeroMesa());
                    mesa.setDisponible(mesaDetails.isDisponible());
                    mesaService.guardarMesa(mesa);
                    return "redirect:/admins/mesas";
                })
                .orElse("redirect:/admins/mesas");
    }

    @GetMapping("/mesas/eliminar/{id}")
    public String deleteMesa(@PathVariable Long id) {
        mesaService.deleteById(id);
        return "redirect:/admins/mesas";
    }

    // --------- Rutas para RESERVAS ---------

    @GetMapping("/reservas")
    public String listarReservas(Model model) {
        model.addAttribute("reservas", reservaService.obtenerTodasReservas());
        return "admins/reservas/reservasList"; // Vista para listar reservas
    }

    @GetMapping("/reservas/nueva")
    public String formularioNuevaReserva(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("mesasDisponibles", mesaService.obtenerMesasDisponibles());
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        return "admins/reservas/reservasForm"; // Vista para crear una nueva reserva
    }

    @PostMapping("/reservas/nueva")
    public String crearReserva(@ModelAttribute Reserva reserva) {
        if (!mesaService.verificarDisponibilidad(reserva.getMesa().getId())) {
            // Agregar mensaje de error si la mesa no está disponible
            return "redirect:/admins/reservas/nueva?error=mesaNoDisponible";
        }
        reservaService.crearReserva(reserva);
        return "redirect:admins/reservas/lista"; // Redirige a la lista de reservas
    }

    @GetMapping("/reservas/editar/{id}")
    public String formularioEditarReserva(@PathVariable Long id, Model model) {
        Reserva reserva = reservaService.obtenerReservaPorId(id);
        if (reserva == null) {
            return "redirect:/admins/reservas";
        }
        model.addAttribute("reserva", reserva);
        model.addAttribute("mesasDisponibles", mesaService.obtenerMesasDisponibles());
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        return "admins/reservas/reservasForm"; // Vista para editar una reserva
    }

    @PostMapping("/reservas/editar/{id}")
    public String actualizarReserva(@PathVariable Long id, @ModelAttribute Reserva reservaDetalles) {
        reservaService.actualizarReserva(reservaDetalles);
        return "redirect:/admins/reservas"; // Redirige a la lista de reservas
    }

    @GetMapping("/reservas/eliminar/{id}")
    public String eliminarReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
        return "redirect:/admins/reservas"; // Redirige a la lista de reservas
    }
}