package org.example.turismojdbc;

import org.example.turismojdbc.model.ServicioComplementario;

import java.util.ArrayList;
import java.util.List;

public class ServicioCostoForm {

    private List<ServicioSeleccionado> serviciosSeleccionados = new ArrayList<>();

    private ServicioComplementario nuevoServicio; // para crear nuevo

    // getters y setters...

    public List<ServicioSeleccionado> getServiciosSeleccionados() {
        return serviciosSeleccionados;
    }

    public void setServiciosSeleccionados(List<ServicioSeleccionado> serviciosSeleccionados) {
        this.serviciosSeleccionados = serviciosSeleccionados;
    }

    public ServicioComplementario getNuevoServicio() {
        return nuevoServicio;
    }

    public void setNuevoServicio(ServicioComplementario nuevoServicio) {
        this.nuevoServicio = nuevoServicio;
    }

    // Este es el método que inicializa la lista a partir de los servicios disponibles
    public void inicializarDesdeServicios(List<ServicioComplementario> servicios) {
        serviciosSeleccionados = new ArrayList<>();
        for (ServicioComplementario servicio : servicios) {
            ServicioSeleccionado sel = new ServicioSeleccionado();
            sel.setServicioId(servicio.getId());
            sel.setSeleccionado(false); // por defecto no seleccionado
            serviciosSeleccionados.add(sel);
        }
    }
}
