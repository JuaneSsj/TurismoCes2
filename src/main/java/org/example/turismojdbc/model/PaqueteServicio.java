package org.example.turismojdbc.model;

import org.springframework.data.relational.core.mapping.Table;


public class PaqueteServicio {

    private Long paqueteId;
    private Long servicioId;

    public PaqueteServicio() {}
    public PaqueteServicio(Long paqueteId, Long servicioId) {
        this.paqueteId = paqueteId;
        this.servicioId = servicioId;
    }

    public Long getPaqueteId() { return paqueteId; }
    public void setPaqueteId(Long paqueteId) { this.paqueteId = paqueteId; }

    public Long getServicioId() { return servicioId; }
    public void setServicioId(Long servicioId) { this.servicioId = servicioId; }
}