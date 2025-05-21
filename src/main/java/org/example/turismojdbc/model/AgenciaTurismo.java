package org.example.turismojdbc.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.example.turismojdbc.model.enums.TipoAgencia;

@Data
@Table(schema = "turismoces2", name = "agenciaturismo")
public class AgenciaTurismo {
    @Id
    private Long id;
    private String ciudad;
    private String email;
    @Column("tipoagencia") // Nombre exacto en la BD
    private TipoAgencia tipoAgencia;

    private String telefono;

    public Long getId() {
        return id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoAgencia getTipoAgencia() {
        return tipoAgencia;
    }

    public void setTipoAgencia(TipoAgencia tipoAgencia) {
        this.tipoAgencia = tipoAgencia;
    }



}
