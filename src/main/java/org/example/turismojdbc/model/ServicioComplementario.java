package org.example.turismojdbc.model;

import org.example.turismojdbc.model.enums.TipoServicio;
import org.springframework.data.annotation.Id;

public class ServicioComplementario {

    @Id
    private Long id;
    private String nombre;
    private String descripcion;
    private Double costo;
    private TipoServicio tipo;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getCosto() { return costo; }
    public void setCosto(Double costo) { this.costo = costo; }

    public TipoServicio getTipo() { return tipo; }
    public void setTipo(TipoServicio tipo) { this.tipo = tipo; }

    @Override
    public String toString() {
        return "ServicioComplementario [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion +
                ", costo=" + costo + ", tipo=" + tipo + "]";
    }
}