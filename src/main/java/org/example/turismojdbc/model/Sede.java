package org.example.turismojdbc.model;

import org.example.turismojdbc.model.enums.CategoriaSede;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;

public class Sede {

    @Id
    private Long id;

    private String ciudad;
    private String direccion;
    private String gerente;

    @Column("categoriaSede")  // nombre real en la base de datos
    private CategoriaSede categoriaSede;

    @Column("agencia_id")     // nombre correcto de la columna FK
    private AggregateReference<AgenciaTurismo, Long> agenciaId;



    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getGerente() { return gerente; }
    public void setGerente(String gerente) { this.gerente = gerente; }

    public CategoriaSede getCategoriaSede() { return categoriaSede; }
    public void setCategoriaSede(CategoriaSede categoriaSede) { this.categoriaSede = categoriaSede; }

    public AggregateReference<AgenciaTurismo, Long> getAgenciaId() { return agenciaId; }
    public void setAgenciaId(AggregateReference<AgenciaTurismo, Long> agenciaId) { this.agenciaId = agenciaId; }

    @Override
    public String toString() {
        return "Sede [id=" + id + ", ciudad=" + ciudad + ", direccion=" + direccion + ", gerente=" + gerente +
                ", categoria=" + categoriaSede + "]";
    }
}