package org.example.turismojdbc;

public class ServicioAsignadoDTO {
    private Long servicioId;
    private String nombre;
    private String descripcion;
    private Double costoPersonalizado;

    // Constructor
    public ServicioAsignadoDTO(Long servicioId, String nombre, String descripcion, Double costoPersonalizado) {
        this.servicioId = servicioId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costoPersonalizado = costoPersonalizado;
    }

    // Getters y setters
    public Long getServicioId() { return servicioId; }
    public void setServicioId(Long servicioId) { this.servicioId = servicioId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getCostoPersonalizado() { return costoPersonalizado; }
    public void setCostoPersonalizado(Double costoPersonalizado) { this.costoPersonalizado = costoPersonalizado; }
}