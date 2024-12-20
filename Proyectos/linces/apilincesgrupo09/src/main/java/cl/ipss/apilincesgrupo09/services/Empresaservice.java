package cl.ipss.apilincesgrupo09.services;

import cl.ipss.apilincesgrupo09.models.Empresa;
import org.springframework.stereotype.Service;

@Service
public class Empresaservice {

    // Método para mostrar la información de la empresa, incluyendo el teléfono
    public void mostrarInformacionEmpresa(Empresa empresa) {
        System.out.println("Nombre de la empresa: " + empresa.getNombre());
        System.out.println("Dirección de la empresa: " + empresa.getDireccion());
        System.out.println("Teléfono de la empresa: " + empresa.getTelefono());  // Usando el teléfono
    }

    
    public void actualizarTelefono(Empresa empresa, String nuevoTelefono) {
        empresa.setTelefono(nuevoTelefono);  // Actualizando el teléfono
    }
}
