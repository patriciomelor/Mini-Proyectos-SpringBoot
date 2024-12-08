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
import com.ev.linces.services.ClienteService;

@Controller
@RequestMapping("/clientes") // Base de todas las rutas será "/clientes"
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
   
    // Ruta para listar clientes
    @GetMapping("/list")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        return "clientes/clientesList"; // Vista en templates/clientes/clientesList.html
    }

    // Ruta para el formulario de creación de cliente
    @GetMapping("/form")
    public String formularioNuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/clientesForm"; // Vista en templates/clientes/clientesForm.html
    }

    // Ruta para crear un nuevo cliente (POST)
    @PostMapping("/form")
    public String crearCliente(@ModelAttribute Cliente cliente) {
        clienteService.crearCliente(cliente);
        return "redirect:/clientes/list"; // Redirige a la lista de clientes
    }

    // Ruta para mostrar el formulario de edición de cliente
    @GetMapping("/editar/{id}")
    public String formularioEditarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.obtenerClientePorId(id).orElse(null);
        if (cliente == null) {
            return "redirect:/clientes/list"; // Redirige a la lista si no se encuentra el cliente
        }
        model.addAttribute("cliente", cliente);
        return "clientes/clientesForm"; // Vista en templates/clientes/clientesForm.html
    }

    // Ruta para actualizar un cliente (POST)
    @PostMapping("/editar/{id}")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute Cliente clienteDetalles) {
        clienteService.obtenerClientePorId(id).ifPresent(cliente -> {
            cliente.setNombreCliente(clienteDetalles.getNombreCliente());
            cliente.setTelefonoCliente(clienteDetalles.getTelefonoCliente());
            cliente.setEmailCliente(clienteDetalles.getEmailCliente());
            clienteService.crearCliente(cliente);
        });
        return "redirect:/clientes/list"; // Redirige a la lista después de actualizar
    }

    // Ruta para eliminar un cliente
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/clientes/list"; // Redirige a la lista después de eliminar
    }
}