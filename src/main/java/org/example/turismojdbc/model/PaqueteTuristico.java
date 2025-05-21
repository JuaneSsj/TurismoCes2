package org.example.turismojdbc.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.turismojdbc.model.enums.DuracionPaquete;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Table(schema = "turismoces2", name = "paqueteturistico")
public class PaqueteTuristico {

    @Id
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private Double precio;

    @NotNull(message = "Debe seleccionar una duración")
    private DuracionPaquete duracion;

    private AggregateReference<AgenciaTurismo, Long> agenciaId;

    // N:M con servicios (solo locales)
    @Transient
    private Set<ServicioComplementario> servicios = new HashSet<>();

    public void addServicio(ServicioComplementario sc) {
        servicios.add(sc);
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public DuracionPaquete getDuracion() { return duracion; }
    public void setDuracion(DuracionPaquete duracion) { this.duracion = duracion; }

    public AggregateReference<AgenciaTurismo, Long> getAgenciaId() { return agenciaId; }
    public void setAgenciaId(AggregateReference<AgenciaTurismo, Long> agenciaId) { this.agenciaId = agenciaId; }

    public Set<ServicioComplementario> getServicios() { return servicios; }
    public void setServicios(Set<ServicioComplementario> servicios) { this.servicios = servicios; }

    @Override
    public String toString() {
        return "PaqueteTuristico [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion +
                ", precio=" + precio + ", duracion=" + duracion + "]";
    }
}