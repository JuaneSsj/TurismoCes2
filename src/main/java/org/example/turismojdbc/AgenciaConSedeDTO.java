package org.example.turismojdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.turismojdbc.model.AgenciaTurismo;
import org.example.turismojdbc.model.Sede;

@Data
public class AgenciaConSedeDTO {
    private AgenciaTurismo agencia;
    private Sede sede;

    public AgenciaConSedeDTO(AgenciaTurismo agencia, Sede sede) {
        this.agencia = agencia;
        this.sede = sede;
    }

    // Getters y setters
    public AgenciaTurismo getAgencia() {
        return agencia;
    }

    public void setAgencia(AgenciaTurismo agencia) {
        this.agencia = agencia;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }
}